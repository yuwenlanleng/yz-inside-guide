/*
 * ChgUserInfoPanel.java
 *
 * Created on May 18, 2010, 10:10:34 AM
 */
package com.nazca.inside.guide.client;

import com.nazca.ui.NInternalDiag;
import com.nazca.ui.NInternalDiagListener;
import com.nazca.ui.NLabelMessageTool;
import com.nazca.usm.model.USMSUser;
import com.nazca.inside.guide.client.agent.ChgPwdAgent;
import com.nazca.inside.guide.client.listener.ChgPwdAgentListener;
import com.nazca.inside.guide.client.ui.common.OKCancelPanelListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author blader
 */
public class ChgUserInfoPanel extends javax.swing.JPanel implements ChgPwdAgentListener {

    /** Creates new form ChgUserInfoPanel */
    public ChgUserInfoPanel() {
        initComponents();

        oKCancelPanel1.addOKCancelListener(new OKCancelPanelListener() {
            @Override
            public void onOKClicked() {
                String old = new String(jPasswordField1.getPassword());
                String new1 = new String(jPasswordField2.getPassword());
                String new2 = new String(jPasswordField3.getPassword());
                if (old.length() < 1) {
                    oKCancelPanel1.gotoErrorMode("请填写旧密码！");
                    return;
                }
                if (!isPwdOK(new1)) {
                    oKCancelPanel1.gotoErrorMode("请填写长度超过6位的新密码！");
                    jPasswordField2.setText("");
                    jPasswordField3.setText("");
                    return;
                }
                if (!new1.equals(new2)) {
                    oKCancelPanel1.gotoErrorMode("两次输入的新密码不相同！");
                    jPasswordField2.setText("");
                    jPasswordField3.setText("");
                    return;
                }
                lockUI(true);
                USMSUser user = (USMSUser) ClientContext.getUser();
                agent.startChgPwd(user.getLoginName(), old, new1);
                oKCancelPanel1.gotoWaitMode("正在修改...");
            }

            @Override
            public void onCancelClicked() {
                jPasswordField1.setText("");
                jPasswordField2.setText("");
                jPasswordField3.setText("");
            }
        });
        
        oKCancelPanel1.gotoNormalMode();
        NLabelMessageTool.warningMessage(this.jLabel6);
        this.agent = new ChgPwdAgent();
        agent.addChgPwdAgentListener(this);
    }
    private ChgPwdAgent agent = null;

    private boolean isPwdOK(String pwd) {
        if (pwd.length() > 5) {
            return true;
        } else {
            return false;
        }
    }

    private void lockUI(boolean f) {
        this.jPasswordField1.setEnabled(!f);
        this.jPasswordField2.setEnabled(!f);
        this.jPasswordField3.setEnabled(!f);
        this.jToolBar1.setEnabled(!f);
        this.jToggleButton1.setEnabled(!f);
        if(f){
            oKCancelPanel1.gotoDisableMode();
        }else{
            oKCancelPanel1.gotoNormalMode();
        }
    }

    public void showMe(JComponent parent){
        NInternalDiag<Object, JComponent> diag = new NInternalDiag<Object, JComponent>("修改个人信息", null, this);
        diag.setCloseButtonVisible(true);
        diag.addNInternalDiagListener(new NInternalDiagListener() {

            @Override
            public void onClosing(NInternalDiag nid) {
                NInternalDiag diag = NInternalDiag.findNInternalDiag(ChgUserInfoPanel.this);
                diag.hideDiag();
            }

            @Override
            public void onClosed(NInternalDiag nid) {
            }

            @Override
            public void onShowingDone(NInternalDiag nid) {
            }
        });
        diag.showInternalDiag(parent);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToolBar1 = new javax.swing.JToolBar();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        oKCancelPanel1 = new com.nazca.inside.guide.client.ui.common.OKCancelPanel();

        buttonGroup1.add(jToggleButton2);
        jToggleButton2.setText("个人信息");
        jToggleButton2.setEnabled(false);
        jToggleButton2.setFocusable(false);
        jToggleButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        buttonGroup1.add(jToggleButton1);
        jToggleButton1.setSelected(true);
        jToggleButton1.setText("密码修改");
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jToggleButton1);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(3, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 21, Short.MAX_VALUE)
        );

        jToolBar1.add(jPanel2);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jLabel1.setText("<html>请输入您当前使用的密码，以及你想要变更的密码（输入两遍），点击修改密码开始进行密码修改。“同时更新登录时记住密码”选项只有在您开启了“记住密码”才会生效。</html>");

        jLabel2.setText("旧密码：");

        jLabel3.setText("新密码：");

        jLabel4.setText("再次输入新密码：");

        jLabel6.setText("注意：新密码长度必须大于或者等于6位，建议同时含字母和数字。");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField3)
                            .addComponent(jPasswordField2)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 56, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
        add(oKCancelPanel1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToolBar jToolBar1;
    private com.nazca.inside.guide.client.ui.common.OKCancelPanel oKCancelPanel1;
    // End of variables declaration//GEN-END:variables

    public void onStartChgPwd(String userid) {
    }

    public void onPwdChgSuc(String userid) {
        oKCancelPanel1.gotoSuccessMode("密码修改成功！");
        String newPWD = new String(this.jPasswordField2.getPassword());
        this.jPasswordField1.setText("");
        this.jPasswordField2.setText("");
        this.jPasswordField3.setText("");
        USMSUser user = (USMSUser) ClientContext.getUser();
        user.setPassword(newPWD);
        ClientContext.setPassword(newPWD);
        JOptionPane.showMessageDialog(this, "您的密码已经成功更新。", "通知", JOptionPane.PLAIN_MESSAGE);
        NInternalDiag diag = NInternalDiag.findNInternalDiag(ChgUserInfoPanel.this);
        diag.hideDiag();
    }

    public void onPwdChgFailed(String userid, String msg, int code) {
        lockUI(false);
        oKCancelPanel1.gotoErrorMode(msg, code);
    }
}
