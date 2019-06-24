package ext3d.sys;

public final class Ext {
//	<Methods>
//		<Static>
			public static void          out(Exception err){
				err.printStackTrace();
			}
			public static void          out(Object... obj_Lines){
				for(Object o : obj_Lines) {
					System.out.println(o);
				}
			}
			public static void          ptr(Object... obj_Lines){
				for(Object o : obj_Lines){
					System.err.println(o);
				}
			}
			public static void          ppn(String str_Prefix, Object... obj_Lines){
				for(Object o : obj_Lines){
					System.out.print(str_Prefix);
					System.out.println(o);
				}
			}
//		</Static>	
//	</Methods>
}
