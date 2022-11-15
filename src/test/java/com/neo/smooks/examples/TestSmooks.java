package com.neo.smooks.examples;

import org.smooks.Smooks;
import org.smooks.cartridges.json.JSONReaderConfigurator;
import org.smooks.cartridges.templating.TemplatingConfiguration;
import org.smooks.cartridges.templating.freemarker.FreeMarkerTemplateProcessor;
import org.testng.annotations.Test;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;

public class TestSmooks {

    @Test
    public void testJ2JWithJb() throws Exception{
        Smooks smooks = new Smooks("smooks-j2j-config.xml");
        smooks.setReaderConfig(new JSONReaderConfigurator()
                .setRootName("order")
                .setArrayElementName("e"));
        smooks.addVisitor(new FreeMarkerTemplateProcessor(new TemplatingConfiguration("order.ftl")), "order");
        smooks.filterSource(new StreamSource(new FileInputStream("./src/test/resources/order.json")), new StreamResult(System.out));
    }

    @Test
    public void testJ2JWithoutJb() throws Exception{
        Smooks smooks = new Smooks();
        smooks.setReaderConfig(new JSONReaderConfigurator()
                .setRootName("order")
                .setArrayElementName("e"));
        smooks.addVisitor(new FreeMarkerTemplateProcessor(new TemplatingConfiguration("order.ftl")), "order");
        smooks.filterSource(new StreamSource(new FileInputStream("./src/test/resources/order.json")), new StreamResult(System.out));
    }




}
