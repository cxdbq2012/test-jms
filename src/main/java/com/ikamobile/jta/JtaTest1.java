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
            // ��� Transaction �������
            userTx = null;
            // �����ݿ� A ��ȡ�����ݿ�����
            connA = getConnectionA();

            // �����ݿ� B ��ȡ�����ݿ�����
            connB = getConnectionB();

            // ��������
            userTx.begin();

            // �� A �˻��еĽ����� 500
            stmtA = connA.createStatement();
            stmtA.execute("insert into tmp (uuid,id) values ('2','2')");

                    // �� B �˻��еĽ������ 500
            stmtB = connB.createStatement();
            stmtB.execute("insert into tmp (uuid,id) values ('1','1')");

                    // �ύ����
            userTx.commit();
            // �����ύ��ת�˵���������ͬʱ�ɹ������ݿ� A �����ݿ� B �е����ݱ�ͬʱ���£�
        } catch(SQLException sqle){

            try{
                // �����쳣���ع��ڱ������еĲ���
                userTx.rollback();
                // ����ع���ת�˵�����������ȫ����
                //( ���ݿ� A �����ݿ� B �е����ݸ��±�ͬʱ������

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
