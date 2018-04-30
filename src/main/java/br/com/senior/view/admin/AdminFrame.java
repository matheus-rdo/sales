package br.com.senior.view.admin;

import br.com.senior.control.admin.AdminControl;
import br.com.senior.view.admin.product.ProductForm;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminFrame extends javax.swing.JFrame {

    @Autowired
    private ProductForm productForm;
    
    @Autowired
    private AdminControl adminControl;

    public AdminFrame() {
        initComponents();
        setLocationRelativeTo(null);    
    }
    
    public void refreshComponents() {
    	BigDecimal totalSold = adminControl.getTotalSold();
    	if(totalSold == null) {
    		totalSold = BigDecimal.ZERO;
    	}
    	lblTotalSold.setText(totalSold.toString()); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        optionsPanel = new javax.swing.JPanel();
        btRegisterProduct = new javax.swing.JButton();
        lblSales = new javax.swing.JLabel();
        lblTotalSold = new javax.swing.JLabel();
        lblSoldPrefix = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Vendas");

        lblAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdmin.setText("Painel do Administrador");

        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));

        btRegisterProduct.setText("Cadastro de produto");
        btRegisterProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btRegisterProduct)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btRegisterProduct)
                .addContainerGap(159, Short.MAX_VALUE))
        );

        lblSales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSales.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSales.setText("Total de vendas");

        lblTotalSold.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTotalSold.setForeground(new java.awt.Color(0, 102, 0));
        lblTotalSold.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalSold.setText("123.80");

        lblSoldPrefix.setText("R$");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSoldPrefix)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTotalSold, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdmin)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblSales)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalSold)
                            .addComponent(lblSoldPrefix))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegisterProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterProductActionPerformed
        productForm.setVisible(true);
    }//GEN-LAST:event_btRegisterProductActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRegisterProduct;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblSales;
    private javax.swing.JLabel lblSoldPrefix;
    private javax.swing.JLabel lblTotalSold;
    private javax.swing.JPanel optionsPanel;
    // End of variables declaration//GEN-END:variables
}
