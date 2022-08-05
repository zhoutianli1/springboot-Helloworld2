package com.zhou;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootHelloworldApplicationTests {
    //加了mysql驱动，配置了连接数据库yml，自动生成相应对象
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        //查看一下默认数据源
        System.out.println("目前连接的数据源是："+dataSource.getClass());    //DataSourceConfi... 中有4个数据源，默认是Hikiar, 在yml中用type去更改配置
        //查看数据库连接
        Connection connection =  dataSource.getConnection();
        System.out.println(connection);
        //拿到数据库连接，就可以进行 CURD操作

        //xxxTemplate :是springboot已经配置好的模板bean，  如有jdbc  redis

        //关闭
        connection.close();
    }

}
