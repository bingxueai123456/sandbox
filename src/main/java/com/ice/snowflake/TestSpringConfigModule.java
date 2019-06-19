package com.ice.snowflake;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.ProcessController;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;

import javax.annotation.Resource;


@MetaInfServices(Module.class)
@Information(id = "my-first-sandbox")
public class TestSpringConfigModule implements Module {
	@Resource
	private ModuleEventWatcher moduleEventWatcher;

	/**
	 * 改变JsonTestSpringConfigController的testConfig
	 */
	@Command("changeTestConfig")
	public void testChangeTestConfig() {
		new EventWatchBuilder(moduleEventWatcher)
				.onClass("com.akulaku.snowflake.controller.outer.JsonTestSpringConfigController")
				.onBehavior("testConfig")
				.onWatch(new AdviceListener(){
					@Override
					protected void afterReturning(Advice advice) throws Throwable {
						ProcessController.returnImmediately("I am eclair");
					}
				});


	}

}
