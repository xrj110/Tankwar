package tank;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.Serializable;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

public abstract class ToolkiTS extends Toolkit implements Serializable {
    @Override
    public Dimension getScreenSize() throws HeadlessException {
        return null;
    }

    @Override
    public int getScreenResolution() throws HeadlessException {
        return 0;
    }

    @Override
    public ColorModel getColorModel() throws HeadlessException {
        return null;
    }

    @Override
    public String[] getFontList() {
        return new String[0];
    }

    @Override
    public FontMetrics getFontMetrics(Font font) {
        return null;
    }

    @Override
    public void sync() {

    }

    @Override
    public Image getImage(String filename) {
        return null;
    }

    @Override
    public Image getImage(URL url) {
        return null;
    }

    @Override
    public Image createImage(String filename) {
        return null;
    }

    @Override
    public Image createImage(URL url) {
        return null;
    }

    @Override
    public boolean prepareImage(Image image, int width, int height, ImageObserver observer) {
        return false;
    }

    @Override
    public int checkImage(Image image, int width, int height, ImageObserver observer) {
        return 0;
    }

    @Override
    public Image createImage(ImageProducer producer) {
        return null;
    }

    @Override
    public Image createImage(byte[] imagedata, int imageoffset, int imagelength) {
        return null;
    }

    @Override
    public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
        return null;
    }

    @Override
    public void beep() {

    }

    @Override
    public Clipboard getSystemClipboard() throws HeadlessException {
        return null;
    }

    @Override
    protected EventQueue getSystemEventQueueImpl() {
        return null;
    }

    @Override
    public boolean isModalityTypeSupported(Dialog.ModalityType modalityType) {
        return false;
    }

    @Override
    public boolean isModalExclusionTypeSupported(Dialog.ModalExclusionType modalExclusionType) {
        return false;
    }

    @Override
    public Map<TextAttribute, ?> mapInputMethodHighlight(InputMethodHighlight highlight) throws HeadlessException {
        return null;
    }
}
