package com.howtodoinjava.demo.aspect;

public interface IHelloWorldService {
	public void sayBefore(String param);
	public boolean sayAfterReturning();
	public void sayAfterThrowing();
	public boolean sayAfterFinally();
	public void sayAround(String param);
	public void sayAdvisorBefore(String param);
}
