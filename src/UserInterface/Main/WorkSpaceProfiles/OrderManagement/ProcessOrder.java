/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Main.WorkSpaceProfiles.OrderManagement;

import UserInterface.ProductManagement.*;
import TheBusiness.Business.Business;
import TheBusiness.CustomerManagement.CustomerProfile;
import TheBusiness.OrderManagement.MasterOrderList;
import TheBusiness.OrderManagement.Order;
import TheBusiness.OrderManagement.OrderItem;
import TheBusiness.ProductManagement.Product;
import TheBusiness.ProductManagement.ProductCatalog;
import TheBusiness.ProductManagement.ProductSummary;
import TheBusiness.SalesManagement.SalesPersonProfile;
import TheBusiness.Supplier.Supplier;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kal bugrara
 */
public class ProcessOrder extends javax.swing.JPanel {

    /**
     * Creates new form ManageSuppliersJPanel
     */
    JPanel CardSequencePanel;
    Business business;
    Supplier selectedsupplier;
    Product selectedproduct;
    Order currentOrder;
    CustomerProfile customer;
    SalesPersonProfile salesperson;

    public ProcessOrder(Business bz, CustomerProfile cp, SalesPersonProfile spp, JPanel jp) {

        CardSequencePanel = jp;
        this.business = bz;
        initComponents();
        customer = cp;
        salesperson = spp;
        salesPersonTextField.setText(salesperson.getPerson().toString());
        customerTextField.setText(customer.getCustomerId());
        MasterOrderList mol = business.getMasterOrderList();
        currentOrder =  mol.newOrder(customer, salesperson); //no order was made yet
        initializeTable();
        
        setupEditableColumns();
        setupTableListener();

    }

    private void initializeTable() {

//clear supplier table
        cleanUpCombobox();
        cleanUpTable();

//load suppliers to the combobox
        ArrayList<Supplier> supplierlist = business.getSupplierDirectory().getSuplierList();

        if (supplierlist.isEmpty()) {
            return;
        }

        for (Supplier s : supplierlist) {
            
            SuppliersComboBox.addItem(s.toString());
        }
            SuppliersComboBox.setSelectedIndex(0);
            String suppliername = (String) SuppliersComboBox.getSelectedItem();
            selectedsupplier = business.getSupplierDirectory().findSupplier(suppliername);
            ProductCatalog pc = selectedsupplier.getProductCatalog();
            
            for (Product pt : pc.getProductList()) {

                Object[] row = new Object[5]; //Object[] means an arry of 5 elements
                row[0] = pt;
                row[1] = pt.getFloorPrice();
                row[2] = pt.getCeilingPrice();
                row[3] = pt.getTargetPrice();

                ((DefaultTableModel) SupplierCatalogTable.getModel()).addRow(row);
            }

        
    }

    public void cleanUpCombobox() {
        //Clean the combobox for supplier choices

        SuppliersComboBox.removeAllItems();

    }

    public void cleanUpTable() {

        //Clean the product catalog table
        int rc = SupplierCatalogTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) SupplierCatalogTable.getModel()).removeRow(i);
        }
    }
    public void cleanUpItemsTable() {

        //Clean the product catalog table
        int rc = OrderItemsTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) OrderItemsTable.getModel()).removeRow(i);
        }
    }

    public void refreshSupplierProductCatalogTable() {

//clear supplier table
        int rc = SupplierCatalogTable.getRowCount();
        int i;
        for (i = rc - 1; i >= 0; i--) {
            ((DefaultTableModel) SupplierCatalogTable.getModel()).removeRow(i);
        }

        String suppliername = (String) SuppliersComboBox.getSelectedItem();

        selectedsupplier = business.getSupplierDirectory().findSupplier(suppliername);
        if (selectedsupplier == null) {
            return;
        }
        ProductCatalog pc = selectedsupplier.getProductCatalog();

        for (Product pt : pc.getProductList()) {

            Object[] row = new Object[5];
            row[0] = pt;
            row[1] = pt.getFloorPrice();
            row[2] = pt.getCeilingPrice();
            row[3] = pt.getTargetPrice();
//                row[1] = pt.getPerformanceMeasure();
//               row[2] = la.getName();
            ((DefaultTableModel) SupplierCatalogTable.getModel()).addRow(row);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JButton();
        Next = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        SupplierCatalogTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        OrderItemsTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        SuppliersComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        customerTextField = new javax.swing.JTextField();
        salesPersonTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        productFrequencyBelowTargetTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        productFrequencyAboveTargetTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        productPricePerformanceTextField = new javax.swing.JTextField();
        productRevenueTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        productNameTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));

        Back.setText("X Cancel");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        Next.setText("Submit");
        Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }
        });

        jLabel1.setText("Suppliers");

        SupplierCatalogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name", "Floor", "Ceiling", "Target"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SupplierCatalogTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SupplierCatalogTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SupplierCatalogTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(SupplierCatalogTable);

        jScrollPane2.setViewportView(jScrollPane1);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Prepare Order");

        jLabel8.setText("Product");

        OrderItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Actual price", "Quanity", "Item total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OrderItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OrderItemsTableMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrderItemsTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(OrderItemsTable);

        jScrollPane3.setViewportView(jScrollPane4);

        jLabel9.setText("Order Items");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductItemActionPerformed(evt);
            }
        });

        SuppliersComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuppliersComboBoxActionPerformed(evt);
            }
        });

        jLabel10.setText("Customer");

        jLabel11.setText("Sales person");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Business-wide Product Intelligence", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N

        jLabel6.setText("Frequency Below Target");

        jLabel4.setText("Frequency Above Target");

        jLabel7.setText("Marign around target");

        productPricePerformanceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productPricePerformanceTextFieldActionPerformed(evt);
            }
        });

        productRevenueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productRevenueTextFieldActionPerformed(evt);
            }
        });

        jLabel5.setText("Sales Revenues");

        jLabel3.setText("Product name");

        productNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(productFrequencyAboveTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(productFrequencyBelowTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(productRevenueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(productPricePerformanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productFrequencyAboveTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productFrequencyBelowTargetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productRevenueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productPricePerformanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButton2.setText("Add2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SuppliersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(customerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(salesPersonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Next, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SuppliersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salesPersonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton2)))
                .addGap(30, 30, 30)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(Next)
                        .addGap(17, 17, 17)
                        .addComponent(Back)))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Business -wide Product Intelligence");
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        currentOrder.CancelOrder();
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);

    }//GEN-LAST:event_BackActionPerformed

    private void NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextActionPerformed
        // TODO add your handling code here:
        currentOrder.Submit();
        CardSequencePanel.remove(this);
        ((java.awt.CardLayout) CardSequencePanel.getLayout()).next(CardSequencePanel);


    }//GEN-LAST:event_NextActionPerformed

    private void SupplierCatalogTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupplierCatalogTableMousePressed
        // TODO add your handling code here:
        int suppliertablesize = SupplierCatalogTable.getRowCount();
        int selectedrow = SupplierCatalogTable.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > suppliertablesize - 1) {
            return;
        }
        selectedproduct = (Product) SupplierCatalogTable.getValueAt(selectedrow, 0);
        if (selectedproduct == null) {
            return;
        }

        ProductSummary productsummary = new ProductSummary(selectedproduct);

        productNameTextField.setText(selectedproduct.toString());
        String revenues = String.valueOf(productsummary.getSalesRevenues());
        productRevenueTextField.setText(revenues);
        productFrequencyAboveTargetTextField.setText(String.valueOf(productsummary.getNumberAboveTarget()));
        productFrequencyBelowTargetTextField.setText(String.valueOf(productsummary.getNumberBelowTarget()));
        productPricePerformanceTextField.setText(String.valueOf(productsummary.getProductPricePerformance()));

    }//GEN-LAST:event_SupplierCatalogTableMousePressed

    private void productNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameTextFieldActionPerformed

    private void productRevenueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productRevenueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productRevenueTextFieldActionPerformed

    private void productPricePerformanceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productPricePerformanceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productPricePerformanceTextFieldActionPerformed

    private void SupplierCatalogTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SupplierCatalogTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SupplierCatalogTableMouseEntered

    private void OrderItemsTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderItemsTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderItemsTableMouseEntered

    private void OrderItemsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderItemsTableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderItemsTableMousePressed

    private void AddProductItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductItemActionPerformed
        // TODO add your handling code here:

        int suppliertablesize = SupplierCatalogTable.getRowCount(); 
        int selectedrow = SupplierCatalogTable.getSelectionModel().getLeadSelectionIndex();

        if (selectedrow < 0 || selectedrow > suppliertablesize - 1) {
            return; //no thanks
        }
        selectedproduct = (Product) SupplierCatalogTable.getValueAt(selectedrow, 0);
        if (selectedproduct == null) return;
        
        OrderItem item = currentOrder.newOrderItem(selectedproduct, 1000, 1);
            Object[] row = new Object[5];

            row[0] = item;
            row[1] = String.valueOf(item.getActualPrice());
            row[2] = String.valueOf(item.getQuantity());
            row[3] = String.valueOf(item.getOrderItemTotal());

            ((DefaultTableModel) OrderItemsTable.getModel()).addRow(row);
 
            

    }//GEN-LAST:event_AddProductItemActionPerformed

    private void SuppliersComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuppliersComboBoxActionPerformed
        // TODO add your handling code here:
     refreshSupplierProductCatalogTable();
    }//GEN-LAST:event_SuppliersComboBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //1. How many rows do we have in the table ??
        //2. What the row number that selected ??
        //2.5 Is the row rumber within range
        //3. Extract the data in the row (product) ??
        //4. Add the selected product to the shopping cart. ??
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Next;
    private javax.swing.JTable OrderItemsTable;
    private javax.swing.JTable SupplierCatalogTable;
    private javax.swing.JComboBox<String> SuppliersComboBox;
    private javax.swing.JTextField customerTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField productFrequencyAboveTargetTextField;
    private javax.swing.JTextField productFrequencyBelowTargetTextField;
    private javax.swing.JTextField productNameTextField;
    private javax.swing.JTextField productPricePerformanceTextField;
    private javax.swing.JTextField productRevenueTextField;
    private javax.swing.JTextField salesPersonTextField;
    // End of variables declaration//GEN-END:variables
    
    private void setupEditableColumns() {
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	OrderItemsTable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(tf1));
	OrderItemsTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(tf2));
    }
	
    private void setupTableListener() {
	DefaultTableModel model = (DefaultTableModel) OrderItemsTable.getModel();
	
	model.addTableModelListener(e -> {
	    if (e.getType() != TableModelEvent.UPDATE) return;
	
	    int row = e.getFirstRow();
	    int col = e.getColumn();
	    if (col != 1 && col != 2) return;
	
	    OrderItem item = (OrderItem) model.getValueAt(row, 0); 
	
	    Object newValue = model.getValueAt(row, col);
	    int newVal = (newValue instanceof Number) ? ((Number) newValue).intValue()
	                                                   : Integer.parseInt(newValue.toString().trim());
	
	    if (col == 1) {
	        item.setActualPrice(newVal);
	    } else if (col == 2) {
	        item.setQuantity(newVal);
	    }
	    model.setValueAt(item.getOrderItemTotal(), row, 3);
	
	    refreshSummary();
	});
	
	// submit edited value
	OrderItemsTable.putClientProperty("terminateEditOnFocusLost", true);
	OrderItemsTable.setSurrendersFocusOnKeystroke(true);
	
    }
	
    private void refreshSummary() {
	if (selectedproduct == null) return;
	
	ProductSummary s = new ProductSummary(selectedproduct);
	productNameTextField.setText(selectedproduct.toString());
	productRevenueTextField.setText(String.valueOf(s.getSalesRevenues()));
	productFrequencyAboveTargetTextField.setText(String.valueOf(s.getNumberAboveTarget()));
	productFrequencyBelowTargetTextField.setText(String.valueOf(s.getNumberBelowTarget()));
	productPricePerformanceTextField.setText(String.valueOf(s.getProductPricePerformance()));
    }

    
}
