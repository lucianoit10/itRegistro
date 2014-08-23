/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente.utils;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanel_Whit_Image extends JPanel {
 
    private Image image;
 
    public JPanel_Whit_Image(){
    	
    }
 
    public JPanel_Whit_Image(String nameImage) {
        if (nameImage != null) {
            image = new ImageIcon(getClass().getResource(nameImage)).getImage();
        }
    }
    
    public JPanel_Whit_Image(Image initialImage) {
        if (initialImage != null) {
            image = initialImage;
        }
    }
    
    public void setImage(String nameImage) {
        if (nameImage != null) {
            image = new ImageIcon(getClass().getResource(nameImage)).getImage();
        } else {
            image = null;
        }

        repaint();
    }

    public void setImage(Image newImage) {
        image = newImage;

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
        } else {
            setOpaque(true);
        }
        super.paint(g);
    }
 
    //...
}