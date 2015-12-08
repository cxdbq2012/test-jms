package com.ikamobile.jta;

import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by xdb20 on 2015/12/4.
 */
public class JtaTest1 {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    Connection getConnectionA(){
        try {
           return DriverManager.getConnection("jdbc:mysql://192.168.1.134:3306/test?useUnicode=true&characterEncoding=UTF-8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    Connection getConnectionB(){
        try {
            return DriverManager.getConnection("jdbc:mysql://192.168.1.134:3306/xiadongbin?useUnicode=true&characterEncoding=UTF-8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void transferAccount() {
        
        UserTransaction userTx = null;
        Connection connA = null;
        Statement stmtA = null;

        Connection connB = null;
        Statement stmtB = null;

        try{
            // 获得 Transaction 管理对象
            userTx = null;
            // 从数据库 A 中取得数据库连接
            connA = getConnectionA();

            // 从数据库 B 中取得数据库连接
            connB = getConnectionB();

            // 启动事务
            userTx.begin();

            // 将 A 账户中的金额减少 500
            stmtA = connA.createStatement();
            stmtA.execute("insert into tmp (uuid,id) values ('2','2')");

                    // 将 B 账户中的金额增加 500
            stmtB = connB.createStatement();
            stmtB.execute("insert into tmp (uuid,id) values ('1','1')");

                    // 提交事务
            userTx.commit();
            // 事务提交：转账的两步操作同时成功（数据库 A 和数据库 B 中的数据被同时更新）
        } catch(SQLException sqle){

            try{
                // 发生异常，回滚在本事务中的操纵
                userTx.rollback();
                // 事务回滚：转账的两步操作完全撤销
                //( 数据库 A 和数据库 B 中的数据更新被同时撤销）

                stmtA.close();
                connA.close();
                stmtB.close();
                connB.close();

            }catch(Exception ignore){

            }
            sqle.printStackTrace();

        } catch(Exception ne){
            ne.printStackTrace();
        }
    }


}
