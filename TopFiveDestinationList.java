package topfivedestinationlist;

import java.awt.*;
import java.awt.event.*; // Added for MouseListener
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel<TextAndIcon> listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel<>();

        /* Included destination name, description, picture, double clickable text link with color scheme.
           Additional customization is the blue hyperlink */

        addDestinationNameAndPicture(
            "<html><u><font color='blue'>1. Istanbul, Turkey</font></u><br>" +
            "A city that bridges Europe and Asia, rich in culture and history.<br>" +
            "<i>Photo by Hunanuk</i></html>",
            new ImageIcon("resources/Istanbul_1.jpg"),
            "https://www.booking.com/city/tr/Istanbul.html");

        addDestinationNameAndPicture(
            "<html><u><font color='blue'>2. Antalya, Turkey</font></u><br>" +
            "A stunning coastal city known for turquoise beaches and ancient ruins.<br>" +
            "<i>Photo by NomadDaniel</i></html>",
            new ImageIcon("resources/Antalya_2.jpg"),
            "https://www.booking.com/city/tr/Antalya.html");

        addDestinationNameAndPicture(
            "<html><u><font color='blue'>3. Cappadocia, Turkey</font></u><br>" +
            "Famous for its unique rock formations and hot air balloon rides.<br>" +
            "<i>Photo by Ivankazaryan</i></html>",
            new ImageIcon("resources/Cappadocia_3.jpg"),
            "https://www.booking.com/city/tr/Cappadocia.html");

        addDestinationNameAndPicture(
            "<html><u><font color='blue'>4. Ephesus, Turkey</font></u><br>" +
            "An ancient Greek city with remarkable ruins, located near Izmir.<br>" +
            "<i>Photo by Benh LIEU SONG</i></html>",
            new ImageIcon("resources/Izmir_4.jpg"),
            "https://www.booking.com/city/tr/Efes.html");

        addDestinationNameAndPicture(
            "<html><u><font color='blue'>5. Bodrum, Turkey</font></u><br>" +
            "A chic resort town offering nightlife, castles, and turquoise bays.<br>" +
            "<i>Photo by Serhio Magpie</i></html>",
            new ImageIcon("resources/Bodrum_5.jpg"),
            "https://www.booking.com/city/tr/Bodrum.html");

        JList<TextAndIcon> list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);
        list.setCellRenderer(renderer);

        // Add tooltip to indicate clickable
        ToolTipManager.sharedInstance().registerComponent(list);

        // Add double-click to open link in browser
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {  // double-click
                    int index = list.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        TextAndIcon selectedItem = listModel.getElementAt(index);
                        try {
                            Desktop.getDesktop().browse(new java.net.URI(selectedItem.getUrl()));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Unable to open link.");
                        }
                    }
                }
            }
        });

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Added label with my name
        JLabel nameLabel = new JLabel("Created by Seda Cowan");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Added the name label to the bottom (SOUTH) of the layout
        getContentPane().add(nameLabel, BorderLayout.SOUTH);
    }

    private void addDestinationNameAndPicture(String text, Icon icon, String url) {
        TextAndIcon tai = new TextAndIcon(text, icon, url);
        listModel.addElement(tai);
    }
}

class TextAndIcon {
    private String text;
    private Icon icon;
    private String url; // Added for text with embedded link to take user to travel package

    public TextAndIcon(String text, Icon icon, String url) {
        this.text = text;
        this.icon = icon;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getUrl() {
        return url;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer<TextAndIcon> {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<? extends TextAndIcon> list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        TextAndIcon tai = (TextAndIcon) value;
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;
        if (cellHasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // Overridden empty methods for performance
    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}

