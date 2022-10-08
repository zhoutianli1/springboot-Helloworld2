package com.zhou.shiro;


import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的realm
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("给用户授权：");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  //因为业务逻辑是先认证，再授权。在下面的登录认证里，最后的return，已经把用户user放进了SimpleAuthorizationInfo
        //只要经过这个地方，从用户数据库拿到用户的权限。在这里给当前用户授权

        //拿到当前用户的对象subject
        Subject subject=  SecurityUtils.getSubject();
        User currentUser =(User) subject.getPrincipal();  //拿到用户currentUser

        info.addStringPermission(currentUser.getPerms());    //从数据库拿到用户的权限currentAccount.getPerms()，然乎授权给当前用户
        if(currentUser.getPerms().equals("LV1")) {
            System.out.println("目前用户的权限:" + currentUser.getPerms()+"，老板");
        }
        else
        {
            System.out.println("目前用户的权限:" + currentUser.getPerms()+"，系主任");
        }
        return info;
    }

    /**
     * 认证，实现认证：1.获取当前用户Subject-->2.通过当前用户拿到session-->3.封装当前用户 到UsernamePasswordToken
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行登录认证：");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //将上面的参数authenticationToken转换token ，即controller中的UsernamePasswordToken
        //从数据库中拿：

        User user = userMapper.queryUserByName(token.getUsername());

        if (user==null) //用户名这里判段
        {
            return null;//在这里抛出用户名不存在异常，在controller中解决
        }
        //密码认证。shiro做 ，自动将password 与authenticationToken中的pwd比对
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");

    }
}