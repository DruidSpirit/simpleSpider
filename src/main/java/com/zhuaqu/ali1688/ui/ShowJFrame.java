/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhuaqu.ali1688.ui;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.ydj.common.Constant;
import com.ydj.common.dao.DaoFactory;
import com.ydj.simpleSpider.MyLog;


/**
 * 
 * @author : Ares
 * @createTime : Aug 17, 2012 1:49:09 PM
 * @version : 1.0
 * @description :
 */
public class ShowJFrame extends javax.swing.JFrame {
	
	
	private static final long serialVersionUID = 1L;

	private SpiderAli spider ;
	
	private static int sumSuccessCount = 0 ;
	
	private int myHistoryCount = ConfigData.getZhuaquSumCount();

	private boolean resetConfFlag = true;
	
	
    public ShowJFrame(SpiderAli obj) {
    	this();
    	this.spider = obj;
    }
	
	
	/**
     * Creates new form SelectJFrame5
     */
    public ShowJFrame() {// 构造方法是不是太重  O(∩_∩)O哈哈~
        initComponents();
        try {
        	sumSuccessCount = DaoFactory.getMyDao().getAllCount();
		} catch (Exception e) {
		}
        schedule();
    }

    
    
    private Timer timer = new Timer();
    
    private void refresh(){
    	 if(this.spider == null){
    		 return ;
    	 }
    	 
    	 jLabel_requestShow.setText("抓取请求数："+spider.sum);
         jLabel_successShow.setText("抓取成功数："+spider.success);
         jLabel_failShow.setText("抓取失败数："+spider.fail);
         jLabel_sumSuccessShow.setText("总计："+(sumSuccessCount+spider.success)+"  ["+(myHistoryCount+spider.success)+"]");

         if(this.spider.isRun()){
 			this.jButton_stop.setText("暂停,我要修改设置");
 		}else{
 			this.jButton_stop.setText("重新抓取");
 		}
         
         
         Object[] options = { "我已修复，继续抓！", "去一边！" };  
         String alertMsg = "";
         
        switch (Constant.state) {
			case needSignIn:
				alertMsg = "抓取已经暂停，需要在‘浏览器’【重新登录】或【重新设置Cookie和UserAgent】";
				options[0] = "我已重新登录，继续抓！";
				break;
			case needCheckcode:
				alertMsg = "抓取已经暂停，需要在‘浏览器’【输入验证码】";
				options[0] = "我已输入验证码，继续抓！";
				break;
			case gtFailCount:
				alertMsg = "连续"+Constant.alertCount+"次未抓到联系方式，抓取已暂停，请检查是否需要【手动输入验证码】或【重新设置Cookie】或【重新登录】";
				break; 
			default:
			break;
		}
         
         if(Toolbox.isNotEmpty(alertMsg)){
        	
        	int m = -1 ; 
        	if(Constant.state == State.needSignIn){ //
        		//ReSetJFrame reSetFrame = new ReSetJFrame();
        		//reSetFrame.setVisible(true);
        		
//        		String url = "https://login.1688.com/member/signin.htm";
//        		url = "https://sec.1688.com/query.htm?smApp=kylin&smPolicy=kylin-index-anti_Spider-seo-html-checkcode&smCharset=GBK&smTag=NTguMjQ3LjExMi44Miw0NDg2OTQ3MDQsNTY2ZWQ0YzY0MTEwNDNlYTk3NDQ0YTc0OTkxNDNiN2Q%3D&smReturn=https%3A%2F%2Fshop1413621119921.1688.com%2F&smSign=RK3rud0KvlC133bJny4wzQ%3D%3D";
//        		
//        		Test test = new Test(url);
//				test.brower(url, url);
        		
        		if(resetConfFlag){
        			JOptionPane.showMessageDialog(null, alertMsg,"蛋疼",JOptionPane.ERROR_MESSAGE);
        			resetConfFlag = false;
        		}
        		
        		
        	}else {
        		
        		
//        		if(SecondStep2.state == State.needCheckcode){
//            		String url = "https://sec.1688.com/query.htm?smApp=kylin&smPolicy=kylin-index-anti_Spider-seo-html-checkcode&smCharset=GBK&smTag=NTguMjQ3LjExMi44Miw0NDg2OTQ3MDQsNTY2ZWQ0YzY0MTEwNDNlYTk3NDQ0YTc0OTkxNDNiN2Q%3D&smReturn=https%3A%2F%2Fshop1413621119921.1688.com%2F&smSign=RK3rud0KvlC133bJny4wzQ%3D%3D";
//            		
//            		Test test = new Test(url);
//    				test.brower(url, url);
//        		}
        		
        		
        		m = JOptionPane.showOptionDialog(null, alertMsg, "蛋疼",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]); 
        		System.out.println("m:"+m);
        		
        		if(m == 0){
         	    	start();
         	    }else{
         	    	System.exit(-1);
         	    }
        	}
     	    
     	    
         }
	}
    
    private void schedule(){
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				refresh();
			}
		}, 1000 * 1, 1000 * 1);
	}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponents() {

    	this.setTitle("抓 抓 抓...抓个蛋蛋");
    	
        jLabel_requestShow = new javax.swing.JLabel();
        jLabel_successShow = new javax.swing.JLabel();
        jLabel_failShow = new javax.swing.JLabel();
        jLabel_sumSuccessShow = new javax.swing.JLabel();
        
        jButton_stop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_requestShow.setText("抓取请求数：0");
        jLabel_successShow.setText("抓取成功数：0");
        jLabel_failShow.setText("抓取失败数：0");
        jLabel_sumSuccessShow.setText("总计："+sumSuccessCount+"  ["+myHistoryCount+"]");
        
        
        
        
        /**---------------------------------------------------------------------------*/
        jLabel_cookie = new javax.swing.JLabel();
		jTextField_cookie = new javax.swing.JTextField(Constant.cookie);
		
        jLabel_userAgent = new javax.swing.JLabel();
        jTextField_userAgent = new javax.swing.JTextField(Constant.userAgent);
        
        
        jLabel_alertSet = new javax.swing.JLabel();
        jTextField_alertSet = new javax.swing.JComboBox();
        
        jLabel_frequencySet = new javax.swing.JLabel();
        jTextField_frequencySet = new javax.swing.JComboBox();
        
        
        jLabel_userAgent.setText("设置UserAgent");
        jLabel_cookie.setText("设置Cookie");
        jLabel_alertSet.setText("失败N次时提醒");
        jLabel_frequencySet.setText("设置抓取的频率");

        Object items[] = ConfigData.getAlertSet(Constant.alertCount);
        jTextField_alertSet.setModel(new javax.swing.DefaultComboBoxModel(items));
        
        
        Object items2[] = ConfigData.getFrequencySet();
        jTextField_frequencySet.setModel(new javax.swing.DefaultComboBoxModel(items2));
        
        /**---------------------------------------------------------------------------*/
        
        
        
        
        
        
        jButton_stop.setText("暂停，我要修改设置");
       
        jButton_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton_Create_ActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        
        
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            
                            	.addComponent(jLabel_requestShow)
                                .addComponent(jLabel_successShow)
                                .addComponent(jLabel_failShow)
                                .addComponent(jLabel_sumSuccessShow)
                            		
                            	.addComponent(jLabel_userAgent)
                                .addComponent(jLabel_cookie)
                                .addComponent(jLabel_alertSet)
                                .addComponent(jLabel_frequencySet)
                                
                            		)
                            .addGap(45, 45, 45)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField_userAgent,javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(jTextField_cookie,javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                .addComponent(jTextField_alertSet)
                                .addComponent(jTextField_frequencySet, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                
                            		)
                            		
                            		
                        		)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(154, 154, 154)
                            .addComponent(jButton_stop)
                            
                        		))
                    .addContainerGap(58, Short.MAX_VALUE))
            );
        
        
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_requestShow)
                    )
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_successShow)
                    )
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_failShow)
                    )
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_sumSuccessShow)
                    ) 
                 
                  /**---------------------------------------------------------------------------*/
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_userAgent)
                    .addComponent(jTextField_userAgent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    
                    
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_cookie)
                    .addComponent(jTextField_cookie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_alertSet)
                    .addComponent(jTextField_alertSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_frequencySet)
                    .addComponent(jTextField_frequencySet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 /**---------------------------------------------------------------------------*/    
                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)    
                .addComponent(jButton_stop)
                .addContainerGap(27, Short.MAX_VALUE)
                )
        );

        pack();
        
//        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("/ui/logo.png")));
//        this.setIconImage(Toolkit.getDefaultToolkit().createImage(ShowJFrame.class.getResource("/conf/logo.png"))); 
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(ShowJFrame.class.getResource("logo.png"))); 
        
        this.setResizable(false); //设置不可最大化
        this.setLocationRelativeTo(null);//设置居中显示
        
        this.setSize(500, 500);
    }

    
    private void jButton_Create_ActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	if(this.spider.isRun()){
    		this.stop();
    	}else{
	    	String userAgent = this.jTextField_userAgent.getText();
	    	String cookie = this.jTextField_cookie.getText();
	    	Object alertSet = jTextField_alertSet.getSelectedItem();
	    	Object frequencySet = jTextField_frequencySet.getSelectedItem();
	    	
	    	MyLog.logInfo("userAgent: "+userAgent);
	    	MyLog.logInfo("cookie: "+cookie);
	    	MyLog.logInfo("alertSet: "+alertSet.toString());
	    	MyLog.logInfo("frequencySet: "+frequencySet.toString());
	    	
	    	ConfigData.setConfig(userAgent, cookie, alertSet, frequencySet);
	    	
	    	resetConfFlag = true;
	    	
	    	start();
    	}
    	
    }
    
    

	private void start() {
		MyLog.logInfo("start......");
		Constant.state = State.def;
		this.jButton_stop.setText("暂停，我要修改设置");
		
		new Thread(
    			new Runnable() {
					
					@Override
					public void run() {
						try {
							spider.start();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
    	).start();
	}

	
	private void stop() {
		MyLog.logInfo("stop......");
		this.spider.stop();
		this.jButton_stop.setText("重新抓取");
	}
	

	/**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShowJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_requestShow;
    private javax.swing.JLabel jLabel_successShow;
    private javax.swing.JLabel jLabel_failShow;
    private javax.swing.JLabel jLabel_sumSuccessShow;
    
    
    /**-----------------------------------------------------------------*/
    private javax.swing.JLabel jLabel_userAgent;
    private javax.swing.JLabel jLabel_cookie;
    private javax.swing.JLabel jLabel_alertSet;
    private javax.swing.JLabel jLabel_frequencySet;

    private javax.swing.JTextField jTextField_userAgent;
    private javax.swing.JTextField jTextField_cookie;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jTextField_alertSet;
    @SuppressWarnings("rawtypes")
	private javax.swing.JComboBox jTextField_frequencySet;
    /**-----------------------------------------------------------------*/
    
    
    
    
    private javax.swing.JButton jButton_stop;
    // End of variables declaration//GEN-END:variables
}
