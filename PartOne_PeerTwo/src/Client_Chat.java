
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author HITECH
 */
public class Client_Chat extends javax.swing.JFrame {

    /**
     * Creates new form Client_Chat
     */
    public Client_Chat() {
        initComponents();
        getContentPane().setBackground(new Color(224,224,224));
        promptJTextPane();
    }
    public static int index=-1;
    private static Client_Chat f;
    private static HashMap<Integer, String> messages=new HashMap<Integer,String>();
     public static String s;
     public static String st="";
     private static boolean connected=false;
    
    //method from stack over flow to append strings to JTextPane with different colors 
    private static void appendToPane(int index,JTextPane tp, String msg)
    {   
        if(messages.get(index)==null)
            messages.put(index,msg);
        String port[]=msg.split("From");
        port=port[1].split(":");
        String msgPort=port[0];
        msg=msg.replace(msgPort, msgPort.trim().equalsIgnoreCase(f.txtLocalPort.getText())?" Me":" Rem");
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, msgPort.trim().equalsIgnoreCase(f.txtLocalPort.getText())?Color.RED:Color.GREEN);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        s =msg;
        
        tp.replaceSelection(s);
    }
    
//      private static void appendToPane2(JTextPane tp, String msg, Color c){
//        StyleContext sc = StyleContext.getDefaultStyleContext();
//        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
//
//        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
//        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
//
//        int len = tp.getDocument().getLength();
//        tp.setCaretPosition(len);
//        tp.setCharacterAttributes(aset, false);
//      
//        tp.replaceSelection(msg);
//    }
    //method from stack over flow to make a prompt text for JTextPane
    private void promptJTextPane(){
        String proptText = "enter your message";
        txtMsg.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                if(txtMsg.getText().isEmpty()) {
                    txtMsg.setText(proptText);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                if(txtMsg.getText().equals(proptText)) {
                    txtMsg.setText("");
                }
            }
        });
    }
    //method to check if a specific char is found in a given array (used latter for spacial chars checking in handle memory rubbish)
    public static boolean isFound(char [] array,char ch){
        for(char c : array)
            if(c == ch)
                return true;
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMsg = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLocalIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLocalPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRemoteIP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRemotePort = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Index = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Chat");

        txtChat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(txtChat);

        txtMsg.setColumns(20);
        txtMsg.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMsg.setRows(5);
        jScrollPane2.setViewportView(txtMsg);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Status: ");

        txtStatus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Local IP:");

        txtLocalIP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Local Port:");

        txtLocalPort.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Remote IP:");

        txtRemoteIP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Remote Port:");

        txtRemotePort.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnSend.setBackground(new java.awt.Color(204, 204, 204));
        btnSend.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Delete All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Delete Index");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Index.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IndexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(Index, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64))
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtLocalPort, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                            .addComponent(txtRemoteIP, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                            .addComponent(txtRemotePort, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatus)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtLocalIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtLocalPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtRemoteIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtRemotePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSend)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(Index, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if(txtLocalIP.getText().isEmpty() || txtLocalPort.getText().isEmpty() || txtRemoteIP.getText().isEmpty() || txtRemotePort.getText().isEmpty()){
            JOptionPane.showMessageDialog(Client_Chat.this,"Please fill the Local and Remote IP and Port numbers","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //open a UDP socket
            DatagramSocket clientSocket = new DatagramSocket();
            
            //get the address of the server (server is MY PC)
            InetAddress IPAddress = InetAddress.getByName(txtRemoteIP.getText());
            
            //the msg to be sent
            String request = txtMsg.getText();
            index++;
            //update the request by adding the name of the sender
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
            LocalDateTime now = LocalDateTime.now();  
            
            String updatedReuest = index+" From "+f.txtLocalPort.getText()+" : " + request+"     At: " + dtf.format(now) + "\n";
            
            //encapsulate it as a datagram
            DatagramPacket sendPacket = new DatagramPacket(updatedReuest.getBytes(), updatedReuest.getBytes().length,IPAddress,Integer.parseInt(txtRemotePort.getText()));
            //send the datagram to the socket
            clientSocket.send(sendPacket);
            
            //show the msg on the chat
            
            appendToPane(index,txtChat,updatedReuest);
           
            //close the socket
            clientSocket.close();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(Client_Chat.this,"Socket Exception","Unkown Error",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Client_Chat.this,"IO Exception","Unknown Error",JOptionPane.ERROR_MESSAGE);
        } catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(Client_Chat.this,"Port number must be an integer number","Invalid Port Number",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if(txtLocalIP.getText().isEmpty() || txtLocalPort.getText().isEmpty() || txtRemoteIP.getText().isEmpty() || txtRemotePort.getText().isEmpty()){
            JOptionPane.showMessageDialog(Client_Chat.this,"Please fill the Local and Remote IP and Port numbers","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
         
        try {
            //open a UDP socket
            DatagramSocket clientSocket = new DatagramSocket();
            
            //get the address of the server (server is MY PC)
            InetAddress IPAddress = InetAddress.getByName(txtRemoteIP.getText());
            
            //update the request by adding the name of the sender
            String updatedReuest = "delete/U+FE36";
            
            //encapsulate it as a datagram
            DatagramPacket sendPacket = new DatagramPacket(updatedReuest.getBytes(), updatedReuest.getBytes().length,IPAddress,Integer.parseInt(txtRemotePort.getText()));
            //send the datagram to the socket
            clientSocket.send(sendPacket);
            
            //show the msg on the chat
            
            
                        index=-1;
                      txtChat.setText("");
                      messages.clear();
           
           // appendToPane2(txtChat,"Me: " + "", Color.red);
            
            //add the time
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
           // LocalDateTime now = LocalDateTime.now();  
           
           
           // appendToPane2(txtChat,"\t" + dtf.format(now) + "\n", Color.black);
            
            //close the socket
            clientSocket.close();
        } catch (SocketException ex) {
            JOptionPane.showMessageDialog(Client_Chat.this,"Socket Exception","Unkown Error",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Client_Chat.this,"IO Exception","Unknown Error",JOptionPane.ERROR_MESSAGE);
        } catch(NumberFormatException nfe){
            JOptionPane.showMessageDialog(Client_Chat.this,"Port number must be an integer number","Invalid Port Number",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void IndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IndexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IndexActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Integer msgIndex=Integer.parseInt(Index.getText());
        
        try{
        DatagramSocket clientSocket = new DatagramSocket();
            
            InetAddress IPAddress = InetAddress.getByName(txtRemoteIP.getText());
            
            String updatedReuest = "deleteIndex/U+FE36 ,"+msgIndex;
            
            DatagramPacket sendPacket = new DatagramPacket(updatedReuest.getBytes(), updatedReuest.getBytes().length,IPAddress,Integer.parseInt(txtRemotePort.getText()));

            clientSocket.send(sendPacket);
        
        
        deleteFromUi(msgIndex);
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
                java.util.logging.Logger.getLogger(Client_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(Client_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(Client_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(Client_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            
            f= new Client_Chat();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            DatagramSocket serverSocket = null; 
            while(true)
            {
                System.out.println();
                try {
                    byte[] receiveData = new byte[2048];
                    if(f.txtLocalPort.getText().length()==4){
                        
                        if(!connected){
                    serverSocket=new DatagramSocket(Integer.parseInt(f.txtLocalPort.getText()));
                    connected=true;
                    }else{
                   //received data
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    String receviedMsg = new String(receivePacket.getData());
                    if(receviedMsg.contains("delete/U+FE36")){
                        index=-1;
                       f.txtChat.setText("");
                       messages.clear();
                    }else if(receviedMsg.contains("deleteIndex/U+FE36")){
                        String msgAndIndex[]=receviedMsg.split(",");
                        Integer msgIndex =Integer.parseInt(msgAndIndex[1].trim());
                        deleteFromUi(msgIndex);
                    }
                    else{
                    //ignore memory rubbish (size = 2048 bytes)
                    String specialsAsString = "!@#$%^&*():~-_/\\.?,+=><' \n"; char [] specials = specialsAsString.toCharArray();
                    String withoutRubbish = "";
                    //the non-rubbish are: 1- letters 2- digits 3- special chars
                    for(int i=0; i<receviedMsg.length(); i++)
                        if(Character.isLetter(receviedMsg.charAt(i))
                                || Character.isDigit(receviedMsg.charAt(i))
                                || isFound(specials,receviedMsg.charAt(i)))
                            withoutRubbish += receviedMsg.charAt(i);
                    String [] splits = withoutRubbish.split(" "); //to get the port, instead of get the actual port
                  
                   
                    index++;
                    appendToPane(index,f.txtChat, withoutRubbish);
                    
                    f.txtStatus.setText("Received From: IP = "+receivePacket.getAddress().toString().substring(1)+", Port = " + splits[splits.length-1]);
                    }
                    }
                }
            }catch (IOException ex) {
                    f.txtLocalPort.setText("");
                    JOptionPane.showMessageDialog(null,"IO Exception","Unknown Error",JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException nfe){
                    f.txtLocalPort.setText("");
                    JOptionPane.showMessageDialog(null,"Port Number must be an integer","Invalid Port Number",JOptionPane.ERROR_MESSAGE);
                }
            }
        
        //</editor-fold>
    }
    private static void deleteFromUi(Integer msgIndex){
        messages.remove(msgIndex);
        f.txtChat.setText("");
        messages.keySet().stream().forEachOrdered(insideMsgIndex->appendToPane(insideMsgIndex,f.txtChat,messages.get(insideMsgIndex)));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Index;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane txtChat;
    private javax.swing.JTextField txtLocalIP;
    private javax.swing.JTextField txtLocalPort;
    private javax.swing.JTextArea txtMsg;
    private javax.swing.JTextField txtRemoteIP;
    private javax.swing.JTextField txtRemotePort;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}