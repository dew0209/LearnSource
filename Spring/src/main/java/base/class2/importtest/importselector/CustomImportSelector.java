package base.class2.importtest.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Copyright (c) by [安徽航天信息]
 *
 * @author LvLu
 * @className CustomImportSelector
 * @date 2023-12-05 20:04
 * @description
 */
public class CustomImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"base.class1.entity.Fish","base.class1.entity.Tiger"};
	}
}
