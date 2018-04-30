package br.com.senior.view.sale;

import br.com.senior.control.sale.SaleControl;
import br.com.senior.model.domain.document.Document;
import br.com.senior.model.domain.document.NoActiveDocumentException;
import br.com.senior.model.domain.product.Product;
import br.com.senior.model.domain.product.ProductNotFoundException;
import br.com.senior.view.utils.DialogUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleForm extends javax.swing.JFrame {

    @Autowired
    private SaleControl saleControl;

    public SaleForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void refreshStatus() {
        boolean hasActiveSale = saleControl.hasActiveSale();
        lblDocumentNumber.setVisible(hasActiveSale);
        btConfirm.setEnabled(hasActiveSale);
        btCancel.setEnabled(hasActiveSale);
        if(hasActiveSale){
            lblDocumentNumber.setText(saleControl.getDocument().getNumber().toString());
        }
    }

    void refreshComponents() {
        refreshStatus();
        if (saleControl.hasActiveSale()) {
            updateProductsList();
        } else {
            DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();
            tableModel.setRowCount(0);
        }
    }

    private void updateProductsList() {
        Document document = saleControl.getDocument();
        List<Product> items = document.getItems();
        for (Product item : items) {
            addProductToTable(item);
        }
    }

    private void addProductToTable(Product item) {
        DefaultTableModel tableModel = (DefaultTableModel) productsTable.getModel();
        Object rowData[] = new Object[]{
            item.getId(), item.getDescription(), item.getPrice()
        };
        tableModel.addRow(rowData);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSale = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDocumentNumber = new javax.swing.JLabel();
        lblProductsList = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        btCancel = new javax.swing.JButton();
        btConfirm = new javax.swing.JButton();
        lblProduto = new javax.swing.JLabel();
        txtProduto = new javax.swing.JFormattedTextField();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sistema de Vendas");

        lblSale.setText("Venda atual:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Venda");

        lblDocumentNumber.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDocumentNumber.setText("xxx");

        lblProductsList.setText("Lista de produtos:");

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productsTable);

        btCancel.setText("Cancelar");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btConfirm.setText("Confirmar");
        btConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfirmActionPerformed(evt);
            }
        });

        lblProduto.setText("Produto:");

        txtProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProdutoKeyReleased(evt);
            }
        });

        btOk.setText("OK");
        btOk.setToolTipText("Adiciona o produto através de seu código.");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
                        .addComponent(btConfirm))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblProductsList))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProduto)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtProduto)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btOk))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSale)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDocumentNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSale)
                    .addComponent(lblDocumentNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductsList)
                    .addComponent(lblProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btOk)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancel)
                    .addComponent(btConfirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmActionPerformed
        try {
            boolean confirmed = DialogUtils.openConfirmDialog("Deseja realmente confirmar esta venda?");
            if (confirmed) {
                this.saleControl.confirm();
                this.refreshComponents();
            }
        } catch (NoActiveDocumentException ex) {
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btConfirmActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        try {
            boolean confirmed = DialogUtils.openConfirmDialog("Deseja realmente cancelar esta venda?", "Cancelamento de venda");
            if (confirmed) {
                this.saleControl.cancel();
                this.refreshComponents();
            }
        } catch (NoActiveDocumentException ex) {
            Logger.getLogger(SaleForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCancelActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        if (!txtProduto.getText().isEmpty()) {
            long productCode = Long.valueOf(txtProduto.getText());
            try {
                Product newItem = this.saleControl.addProduct(productCode);
                addProductToTable(newItem);
                txtProduto.setText(null);
                this.refreshStatus();
            } catch (ProductNotFoundException ex) {
                DialogUtils.showInfo(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void txtProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdutoKeyReleased
        if(txtProduto.getText().trim().isEmpty()){
            txtProduto.setValue(null);
        }
    }//GEN-LAST:event_txtProdutoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btConfirm;
    private javax.swing.JButton btOk;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDocumentNumber;
    private javax.swing.JLabel lblProductsList;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblSale;
    private javax.swing.JTable productsTable;
    private javax.swing.JFormattedTextField txtProduto;
    // End of variables declaration//GEN-END:variables

}
