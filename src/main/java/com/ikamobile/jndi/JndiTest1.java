package com.ikamobile.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author shizy
 *         <p/>
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JndiTest1 {
    /**
     *
     */
    public JndiTest1() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        try {
            Properties env = new Properties();

            String filep = JndiTest1.class.getResource("/fileSystemService.properties").getFile();
            System.out.println(filep);
            env.load(new FileInputStream( filep));
            env.put(Context.PROVIDER_URL, "file:///c:/");
            Context ctx = new InitialContext(env);
            ctx.createSubcontext("ttf");

            NamingEnumeration list = ctx.list("/");
            while (list.hasMore()) {
                NameClassPair nc = (NameClassPair) list.next();
                System.out.println(nc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}