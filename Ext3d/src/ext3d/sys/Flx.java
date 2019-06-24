package ext3d.sys;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

public final class Flx {
	public static void stream(InputStream in_Stream, OutputStream out_Stream) throws Exception{
		stream(in_Stream,out_Stream,true,true);
	}
	public static void stream(InputStream in_Stream, OutputStream out_Stream, boolean bln_CloseOutput) throws Exception{
		stream(in_Stream,out_Stream,true,bln_CloseOutput);
	}
	public static void stream(InputStream in_Stream, OutputStream out_Stream, boolean bln_CloseInput, boolean bln_CloseOutput) throws Exception{
		byte[] b = new byte[2048];
		for(int l = in_Stream.read(b); l != -1; l = in_Stream.read(b)){
			out_Stream.write(b,0,l);
		}
		if(bln_CloseInput){
			in_Stream.close();
		}
		if(bln_CloseOutput){
			out_Stream.close();
		}
	}
	public static byte[] read(InputStream in_Stream, boolean bln_CloseInput) throws Exception{
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		stream(in_Stream,b,bln_CloseInput,true);
		return b.toByteArray();
	}
	public static void iterateFile(File f, FileIterator fi){
		if(f.exists()){
			if(f.isDirectory()){
				for(File c : f.listFiles()){
					iterateFile(c,fi);
				}
			}
			fi.run(f);
		}
	}
	public static boolean deleteFile(File f){
		if(f.exists()){
			if(f.isDirectory()){
				for(File c : f.listFiles()){
					deleteFile(c);
				}
			}
			f.delete();
			return true;
		}
		return false;
	}
	public static boolean wirteToFile(File f, Object... obj_Lines){
		try {
			FileWriter fw = new FileWriter(f);
			for(Object o : obj_Lines){
				fw.write(Str.valueOf(o));
				fw.write(System.lineSeparator());
			}
			fw.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public interface FileIterator{
		public void run(File f);
	}
}
