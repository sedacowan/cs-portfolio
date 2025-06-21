package slideshow;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class displays a slideshow of the top 5 wellness travel destinations
 * with both images and text descriptions. Users can navigate using "Previous" and "Next" buttons.
 */
public class SlideShow extends JFrame {

    // Declare Panels and aLayouts
    private JPanel slidePane;
    private JPanel textPane;
    private JPanel buttonPane;
    private CardLayout card;       // Controls image slides
    private CardLayout cardText;   // Controls text slides
    private JButton btnPrev;
    private JButton btnNext;

    /**
     * Constructor initializes the UI components
     */
    public SlideShow() throws HeadlessException {
        initComponent();
    }

    /**
     * Initialize all UI components and layout
     */
    private void initComponent() {
        // Initialize layout managers
        card = new CardLayout();
        cardText = new CardLayout();

        // Initialize panels
        slidePane = new JPanel();
        textPane = new JPanel();
        textPane.setBackground(Color.BLUE); // Optional style for text panel
        buttonPane = new JPanel();

        // Configure main JFrame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Top 5 Destinations SlideShow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));

        slidePane.setLayout(card);
        textPane.setLayout(cardText);

        // Add slides (images and text) to panels using CardLayout
        for (int i = 1; i <= 5; i++) {
            JLabel lblSlide = new JLabel();
            JLabel lblTextArea = new JLabel();

            // Load image using helper method
            lblSlide.setIcon(getImageIcon(i));

            // Load descriptive text
            lblTextArea.setText(getTextDescription(i));

            // Add to corresponding card layout panels
            slidePane.add(lblSlide, "card" + i);
            textPane.add(lblTextArea, "cardText" + i);
        }

        // Combine image and text panels vertically
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(slidePane, BorderLayout.CENTER);   // Image at center
        centerPanel.add(textPane, BorderLayout.SOUTH);     // Text below image

        getContentPane().add(centerPanel, BorderLayout.CENTER); // Add combo panel to center

        // Setup navigation buttons
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        btnPrev = new JButton("Previous");
        btnPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goPrevious();
            }
        });

        btnNext = new JButton("Next");
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goNext();
            }
        });

        buttonPane.add(btnPrev);
        buttonPane.add(btnNext);

        // Add button panel to the frame
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

    /**
     * Navigates to the previous image and text slide
     */
    private void goPrevious() {
        card.previous(slidePane);
        cardText.previous(textPane);
    }

    /**
     * Navigates to the next image and text slide
     */
    private void goNext() {
        card.next(slidePane);
        cardText.next(textPane);
    }

    /**
     * Loads an image from the resources folder based on index
     * @param i index of the destination image (1–5)
     * @return ImageIcon for the JLabel
     */
    private ImageIcon getImageIcon(int i) {
        String path = "";
        switch (i) {
            case 1: path = "Wellness1_Yedigoller.jpg"; break;
            case 2: path = "Wellness2_Agonda.jpg"; break; // Updated image for Agonda Village, India
            case 3: path = "Wellness3_WA.jpg"; break;
            case 4: path = "Wellness4_Pamukkale.jpg"; break;
            case 5: path = "Wellness5_Ireland.jpg"; break;
        }

        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("⚠️ Image not found: " + path);
            return new ImageIcon(); // Return empty icon
        }
    }

    /**
     * Provides text description for each destination
     * @param i index of the slide (1–5)
     * @return formatted HTML string
     */
    private String getTextDescription(int i) {
        String text = "";
        switch (i) {
            case 1:
                text = "<html><body><font size='5'><b>#1 Yedigöller National Park, Turkey</b></font><br>"
                     + "Rejuvenate with forest hikes and fresh mountain air by serene lakes.<br>"
                     + "<i>Location:</i> Bolu Province, Turkey<br>"
                     + "<i>Photo Credit:</i> Ömerograf</body></html>";
                break;
            case 2:
                text = "<html><body><font size='5'><b>#2 Agonda Village, India</b></font><br>"
                     + "Relax on the tranquil beaches and enjoy the vibrant local culture.<br>"
                     + "<i>Location:</i> Agonda Beach, Goa, India<br>"
                     + "<i>Photo Credit:</i> Dennis Yang</body></html>";
                break;
            case 3:
                text = "<html><body><font size='5'><b>#3 Olympic National Park, WA, USA</b></font><br>"
                     + "Unplug in lush forests and refresh with mindful meditation.<br>"
                     + "<i>Location:</i> Washington State, USA<br>"
                     + "<i>Photo Credit:</i> Karlyn Langjahr</body></html>";
                break;
            case 4:
                text = "<html><body><font size='5'><b>#4 Pamukkale Thermal Springs, Turkey</b></font><br>"
                     + "Soak in healing waters and explore ancient spa ruins.<br>"
                     + "<i>Location:</i> Denizli Province, Turkey<br>"
                     + "<i>Photo Credit:</i> A. Savin</body></html>";
                break;
            case 5:
                text = "<html><body><font size='5'><b>#5 Lough Erne, County Fermanagh, Northern Ireland</b></font><br>"
                     + "Enjoy our holistic escape on the stunning shores of Lower Lough Erne, "
                     + "at the Spring Awakening Yoga and Dance Retreat.<br>"
                     + "<i>Location:</i> County Fermanagh, Northern Ireland<br>"
                     + "<i>Photo Credit:</i> Skyvido</body></html>";
                break;
        }
        return text;
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SlideShow ss = new SlideShow();
            ss.setVisible(true);
        });
    }
}