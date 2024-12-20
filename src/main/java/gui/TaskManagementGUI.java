package gui;

import model.Task;
import model.TaskManager;
import controller.TaskController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskManagementGUI extends JFrame {

    private TaskManager taskManager;
    private TaskController controller;

    private JTextField taskNameField;
    private JTextArea taskDescriptionField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton markCompletedButton;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public TaskManagementGUI() {
        taskManager = TaskManager.getInstance();
        controller = new TaskController(taskManager);

        // تغيير مظهر الواجهة
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Task Management System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // إضافة شريط أدوات
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout());

        taskNameField = new JTextField(15);
        taskDescriptionField = new JTextArea(3, 15);
        taskDescriptionField.setLineWrap(true);
        taskDescriptionField.setWrapStyleWord(true);

        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");
        markCompletedButton = new JButton("Mark Completed");

        // ألوان مخصصة للأزرار
        addButton.setBackground(Color.GREEN);
        deleteButton.setBackground(Color.RED);
        markCompletedButton.setBackground(Color.CYAN);

        searchField = new JTextField(20);
        searchField.setToolTipText("Search for tasks");

        // إضافة أزرار إلى شريط الأدوات
        toolbar.add(new JLabel("Task Name:"));
        toolbar.add(taskNameField);
        toolbar.add(new JLabel("Description:"));
        toolbar.add(new JScrollPane(taskDescriptionField));
        toolbar.add(addButton);
        toolbar.add(deleteButton);
        toolbar.add(markCompletedButton);
        toolbar.add(new JLabel("Search:"));
        toolbar.add(searchField);

        add(toolbar, BorderLayout.NORTH);

        // إعداد جدول المهام
        tableModel = new DefaultTableModel(new Object[]{"Task Name", "Description", "Status"}, 0);
        taskTable = new JTable(tableModel);
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        // إضافة ActionListener للأزرار
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = taskNameField.getText();
                String description = taskDescriptionField.getText();
                controller.addTask(name, description);
                updateTaskTable();
                taskNameField.setText("");
                taskDescriptionField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this task?", "Delete Task", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        Task task = taskManager.getTasks().get(selectedRow);
                        controller.removeTask(task);
                        updateTaskTable();
                    }
                }
            }
        });

        markCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    Task task = taskManager.getTasks().get(selectedRow);
                    task.markComplete();
                    updateTaskTable();
                }
            }
        });

        // إضافة ميزة البحث
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                String query = searchField.getText().toLowerCase();
                searchTasks(query);
            }
        });

        setVisible(true);
    }

    // تحديث الجدول بعد أي تعديل
    private void updateTaskTable() {
        tableModel.setRowCount(0); // مسح البيانات السابقة
        for (Task task : taskManager.getTasks()) {
            tableModel.addRow(new Object[]{task.getName(), task.getDescription(), task.isCompleted() ? "Completed" : "Pending"});
        }
    }

    // البحث في المهام
    private void searchTasks(String query) {
        tableModel.setRowCount(0); // مسح البيانات السابقة
        for (Task task : taskManager.getTasks()) {
            if (task.getName().toLowerCase().contains(query) || task.getDescription().toLowerCase().contains(query)) {
                tableModel.addRow(new Object[]{task.getName(), task.getDescription(), task.isCompleted() ? "Completed" : "Pending"});
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagementGUI());
    }
}
