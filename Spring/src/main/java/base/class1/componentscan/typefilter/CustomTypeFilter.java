package base.class1.componentscan.typefilter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className CustomTypeFilter
 * @date 2023-12-04 20:21
 * @description
 */
public class CustomTypeFilter implements TypeFilter {

	/**
	 * @Title: match
	 * @Description:
	 * @Author: LvLu
	 * @Date: 2023-12-04 20:23
	 * @param metadataReader 读取到当前正在扫描类的信息
	 * @param metadataReaderFactory 获取其他任何类的信息
	 * @return: boolean
	 * @throws:
	 **/
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		//获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		//获取当前正在扫描的类信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		//获取当前类资源（类的路径）
		Resource resource = metadataReader.getResource();
		System.out.println("annotationMetadata=====>" + annotationMetadata);
		System.out.println("classMetadata=====>" + classMetadata);
		System.out.println("resource=====>" + resource);
		String className = classMetadata.getClassName();
		System.out.println("className=====>" + className);
		System.out.println("metadataReaderFactory=====>" + metadataReaderFactory);
		return className.contains("OrderDao");
	}
}
