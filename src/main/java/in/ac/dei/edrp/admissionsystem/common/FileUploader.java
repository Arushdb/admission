/**
   [Class description.  This class is made for Upload Documents Facility.]
      
   @author <A HREF="mailto:chauhanarjun.chauhan@gmail.com">Arjun Singh Chauhan</A>
   @version $Revision: 1 $Date: 2013/12/19 15:15:25 $
   
**/

package in.ac.dei.edrp.admissionsystem.common;
import javax.servlet.http.HttpServlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FileUploader extends HttpServlet
{
	
	
	public FileUploader() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String fileName = request.getParameter("fileName");
		String folderName = request.getParameter("folderName");
		String filePath =  folderName;
		//response.setContentType("text/plain");
		response.setContentType("text/html") ; //Arush 21-03-2014

		try {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();

			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				
				if (!item.isFormField()) {

					String recievedFileName = item.getName().toLowerCase();

					String fileType = recievedFileName.substring(recievedFileName.lastIndexOf(".") + 1);
					File file = new File(filePath);
					double fileSize = item.getSize()/1024; //IN KiloBytes
					if(fileSize>100)
					{
						response.getWriter().write("N");
						return;
					}
					else if(!(fileType.equalsIgnoreCase("JPG")||fileType.equalsIgnoreCase("JPEG")))
					{
						response.getWriter().write("N");
						return;
					}
					else
					{
						file.mkdirs();
						filePath = filePath + File.separator + fileName + ".jpg";
						System.out.println("file path "+filePath);
					
						byte[] data = item.get();
						FileOutputStream fileOutSt = new FileOutputStream(filePath); 
						fileOutSt.write(data);
						fileOutSt.close();
						response.getWriter().write("Y");
						return;
					}
				} 
			}
			
		} catch (Exception e) {
//			logObj.logger.error(e.getMessage());
			response.getWriter().write("E");
			throw new IOException(e.getMessage());
		}
	}

	}
