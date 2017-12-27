package de.sjantzen.master;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sJantzen on 15.12.2017.
 */
public class Spielwiese {

    public static class TestObject {

        private String name;
        private List<TestObject> childs;

        public TestObject(String name, List<TestObject> childs) {
            this.name = name;
            this.childs = childs;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TestObject> getChilds() {
            return childs;
        }

        public void setChilds(List<TestObject> childs) {
            this.childs = childs;
        }
    }

    public static void main(String [] args) {

        List<TestObject> parents = new ArrayList<>();

        List<TestObject> children1 = new ArrayList<>();
        children1.add(new TestObject("Kind 1 1", null));
        children1.add(new TestObject("Kind 1 2", null));
        children1.add(new TestObject("Kind 1 3", null));

        List<TestObject> children2 = new ArrayList<>();
        children2.add(new TestObject("Kind 2 1", null));
        children2.add(new TestObject("Kind 2 2", null));
        children2.add(new TestObject("Kind 2 3", null));

        parents.add(new TestObject("Parent 1", children1));
        parents.add(new TestObject("Parent 2", children2));

        List<TestObject> allChildren = parents.stream().flatMap(parent -> parent.getChilds().stream()).collect(Collectors.toList());

        for (TestObject child : allChildren) {
            System.out.println("Child: " + child.getName());
        }
    }
}
