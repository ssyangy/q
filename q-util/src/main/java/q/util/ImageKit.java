package q.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class ImageKit {
	   public static BufferedImage load(File imageFile) throws IOException {
		   BufferedImage image = ImageIO.read(imageFile);
		   return image;
		 }
	   public static BufferedImage rotate(BufferedImage bi,int rotate)
	   {
	     AffineTransform af = AffineTransform.getRotateInstance(Math.toRadians(rotate));
	     if(rotate<180){
	    	 af.translate(0,bi.getHeight() * -1);
	     }
	     else if(rotate==180){
	    	 af = new   AffineTransform(-1,0,0,-1,bi.getWidth()-1,bi.getHeight()-1);
	     }
	     else{
	    	 af.translate(bi.getWidth() * -1,0);
	     }
	     AffineTransformOp rOp = new AffineTransformOp(af,null);

	     BufferedImage dstbi = rOp.filter(bi, null);

	     return dstbi;
	   }

	   public static BufferedImage load(InputStream imageFile) throws IOException {
		   BufferedImage image = ImageIO.read(imageFile);
		   return image;
		 }
	    public static BufferedImage cutTo(BufferedImage image,double x1, double y1,double x2,double y2) throws FileNotFoundException {
	          if (image == null) {
	         throw new FileNotFoundException("image file not be load.please execute 'load' function agin.");
	            }

	        int iSrcWidth = image.getWidth(); // 得到源图宽
	        int iSrcHeight = image.getHeight(); // 得到源图长

	            // 如果源图片的宽度或高度小于目标图片的宽度或高度，则直接返回出错
	        if (iSrcWidth < x2 || iSrcHeight < y2) {
	           throw new RuntimeException("source image size too small."+iSrcWidth+" "+x2+" "+iSrcHeight+" "+y2);
	          }
	             image = image.getSubimage((int)x1, (int)y1, (int)x2-(int)x1,(int) y2-(int)y1);
	              return image;
	    }
	    public static BufferedImage zoomTo(BufferedImage image,int maxLength){
	    	  int originWidth=image.getWidth();
	    	  int originHeight=image.getHeight();
               if(originWidth>originHeight){
            		  originHeight=maxLength*originHeight/originWidth;
            		  originWidth=maxLength;
            	  }
            	else{
            		  originWidth=maxLength*originWidth/originHeight;
            		  originHeight=maxLength;
            	}
	          BufferedImage tagImage = new BufferedImage(originWidth, originHeight, BufferedImage.TYPE_INT_RGB); // 缩放图像
	          Image temp=image.getScaledInstance(originWidth, originHeight, Image.SCALE_SMOOTH);
	          Graphics g = tagImage.getGraphics();
	          g.drawImage(temp, 0, 0, null); // 绘制目标图
	          g.dispose();
	          return tagImage;
	    }
	    public static BufferedImage zoomTo(BufferedImage image,int tarWidth, int tarHeight){
	          BufferedImage tagImage = new BufferedImage(tarWidth, tarHeight, BufferedImage.TYPE_INT_RGB); // 缩放图像
	          Image temp=image.getScaledInstance(tarWidth, tarHeight, Image.SCALE_SMOOTH);
	          Graphics g = tagImage.getGraphics();
	          g.drawImage(temp, 0, 0, null); // 绘制目标图
	          g.dispose();
	          return tagImage;
	    }
	    public  static void save(BufferedImage image,String fileName,String formatName) throws IOException{
	    	 // 写文件
	    	 FileOutputStream out = null;
	    	 try {
	    	     ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	     ImageIO.write(image, formatName, bos);// 输出到bos
	    	     out = new FileOutputStream(fileName);
	    	     out.write(bos.toByteArray()); // 写文件
	    	 } catch (IOException e) {
	    	     throw e;
	    	 } finally {
	    	     try {
	    	  if (out != null)
	    	      out.close();
	    	     } catch (IOException e) {

	    	     }
	    	     }
	    	    }
	    /**
	    * 判断文件是否为图片文件(GIF,PNG,JPG)
	    * @param
	    * @return
	    */
	    public static boolean isImage(InputStream imgFile) {
	    byte[] b = new byte[10];
	    int l = -1;
	    try {
	    l = imgFile.read(b);
	    imgFile.close();
	    } catch (Exception e) {
	    return false;
	    }
	    if (l == 10) {
	    byte b0 = b[0];
	    byte b1 = b[1];
	    byte b2 = b[2];
	    byte b3 = b[3];
	    byte b6 = b[6];
	    byte b7 = b[7];
	    byte b8 = b[8];
	    byte b9 = b[9];
	    if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F') {
	    return true;
	    } else if (b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G') {
	    return true;
	    } else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I'&& b9 == (byte) 'F') {
	    return true;
	    } else {
	    return false;
	    }
	    } else {
	    return false;
	    }
	    }
}
