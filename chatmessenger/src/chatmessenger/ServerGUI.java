package chatmessenger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
public class ServerGUI extends javax.swing.JFrame 
{

   TableModel mtm;
   ArrayList<ServerMain.clientHandler>al_onlineclient=new ArrayList<>();
    
    public ServerGUI()
    {
        setLayout(null);
        initComponents();
        setSize(400,400);
        
        mtm=new TableModel();
        jt.setModel(mtm);
    }
     class TableModel extends AbstractTableModel
    {
         String Title[]={"ONLINE IP'S"};

     
         @Override
        public String getColumnName(int a)
        {
         return Title[a];
        }
           @Override
        public int getRowCount()
        {
            return al_onlineclient.size();
        }

        @Override
        public int getColumnCount()
        {
         return Title.length;   
        
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return al_onlineclient.get(rowIndex).ip;
            
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("START");

        jScrollPane1.setViewportView(jt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jButton1)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
class ServerMain implements Runnable
{
 public void run()
 {
  try
  { ServerSocket sersock=new ServerSocket(9900);
    while(true)
    {
       Socket sock=sersock.accept();
       clientHandler obj=new clientHandler(sock);
    }    
          
  }
  catch(Exception ex)
  {
      ex.printStackTrace();
  }    
 }   
     ///inner class///
 class clientHandler implements Runnable
 {Socket sock;
 DataInputStream dis;
 DataOutputStream dos;
 String ip;
 clientHandler(Socket sock) throws IOException
 {
     this.sock=sock;
     dis=new DataInputStream(sock.getInputStream());
     dos=new DataOutputStream(sock.getOutputStream());
     System.out.println("client connected");
     ip=sock.getInetAddress().getHostAddress();
     al_onlineclient.add(this);
     
     mtm.fireTableDataChanged();
     
     Thread t=new Thread(this);
     t.start();
 }
 @Override
 public void run()
 {    
    try
    {
        dos.writeBytes("hello client\r\n");
        String msg=dis.readLine();
        System.out.println(msg);
    }
    catch(Exception ex)
    {
        ex.printStackTrace();
    } 
   }
 }
 }
 private void jButtonActionPerformed(java.awt.event.ActionEvent evt)
 {
     Thread t=new Thread(new ServerMain());
      t.start();
 }       

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
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt;
    // End of variables declaration//GEN-END:variables
}
