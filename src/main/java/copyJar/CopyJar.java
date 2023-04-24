package copyJar;

import cn.hutool.core.util.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class CopyJar {

public static void main(String[] arges){
		// 升级包版本
		String version = "OBEV8.1.3.20230330";
		// 插件导出的目录
		String update = "E:\\product_release\\product_source\\电能e招采\\升级包\\plugins";
		// 产品打版目录
		String productPlugin = "E:\\product_release\\product_source\\电能e招采\\obe_8.0X\\plugins";
		File updateJarName = new File(update+"\\" + "评标工具升级包_"+version+".zip");
		
		Map<String, File> updateFileJarMap = new HashMap<String, File>();
		Stream.of(new File(update).listFiles())
		.filter(f -> f.getName().endsWith(".jar"))
		.forEach(file -> {
			String jarName = file.getName().substring(0, file.getName().indexOf("_"));
			if(updateFileJarMap.containsKey(jarName)) {
				File f1 = updateFileJarMap.get(jarName);
				String f1Version = f1.getName().substring(f1.getName().indexOf("_") + 1, f1.getName().lastIndexOf("."));
				String f2Version = file.getName().substring(file.getName().indexOf("_"), file.getName().lastIndexOf("."));
				// 删除升级包中重复且比较旧的jar包
				if(f2Version.compareTo(f1Version) > 0) {
					System.out.println(jarName +"已存在\t删除"+ f1.getName()+",保留" + file.getName());
					f1.delete();
					updateFileJarMap.put(jarName, file);
				}else {
					System.out.println(jarName +"已存在\t删除"+ file.getName() +",保留" + f1.getName());
					file.delete();
				}
			}else {
				updateFileJarMap.put(jarName, file);
			}
		});
		
		System.out.println("共有"+updateFileJarMap.size()+"个升级包！");
		String[] zipFils = updateFileJarMap.entrySet().stream().map(f -> f.getValue().getAbsolutePath()).toArray(String[]::new);
		try {
			if(updateJarName.exists()) {
				if(!updateJarName.delete()) {
					System.out.println("已存在升级包删除失败！");
				}
			}
			System.out.println("正在压缩升级包...");
			//ZipUtils.zip(zipFils, updateJarName.getAbsolutePath(), "", false, null);
			//ZipUtil.zip()
			System.out.println("升级包压缩完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 读取打包目录下的插件，并替换为最新升级包
		Map<String, File> productPluginJarMap = new HashMap<String, File>();
		Stream.of(new File(productPlugin).listFiles())
		.filter(f -> f.getName().endsWith(".jar"))
		.forEach(file -> {
			String jarName = file.getName().substring(0, file.getName().indexOf("_"));
			productPluginJarMap.put(jarName, file);
		});

		// 将升级包中的插件jar包全部替换到产品打包的插件目录下
		int[] count = new int[] {0};
		updateFileJarMap.forEach((k,f)->{
			File targetFile = productPluginJarMap.get(k);
			if(null != targetFile){
				targetFile.delete();
				System.out.println("已将"+targetFile.getName()+"升级替换为"+f.getName());
			}else {
				System.out.println(f.getName() + "不存在！");
			}
			count[0] ++;
			//FileUtils.copy(f.getAbsolutePath(), productPlugin+"\\"+f.getName());
		});
		
		System.out.println("共升级了"+count[0] +"个升级包！");
		
	}
}
