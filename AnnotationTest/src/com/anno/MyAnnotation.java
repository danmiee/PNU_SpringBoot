/**
 * 
 */
package com.anno;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface MyAnnotation {
	public int count() default 1;	// default 생략갸능
}
