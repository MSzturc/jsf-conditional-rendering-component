jsf-conditional-rendering-component
====================

A JSF Component that allows you to influence the rendering of your JSF views based on the rights the current user has.


Introduction
---------------------

The target of this project is to provide a fluent JSF-API which describes a condition a user has to satisfy to see certain parts of the JSF view. To reach this goal we extended JSF by a new component that allows you to write the following JSF code: 

    
    <f:view>
        <h:form id="adminPanel">
            <x:requires permission="mayAccessAdminPanel" />
            <h:link outcome="/views/admin/index" value="Open Admin Panel"/>
        </h:form>
    </f:view>

By default every permission request through **requires** component will be granted.  We achieved this by implementing a Default Strategy which is used as fallback. It was important to us that without a proper backend implementation your application is still deployable and the rendering of the JSF View wont be affected.

To implement a custom permission check you simply have to implement the following interface:

    public interface PermissionBinding {
        public boolean checkPermission(String... permission);
    }

As you have probably noticed this interface implements only one single method. It represents the glue code that is needed to bind the frontend to your application specific security logic. We didn't want to force you to use our security paradigma, therefore we kept this interface as simple as possible. An implementation may look like this:

    @SessionScoped
    public class MyPermissionBinding implements PermissionBinding {
        
        @Inject User currentUser;
    
        @Override
        public boolean checkPermission(String... permissions) {
            return currentUser.isPermitted(permissions);
        }
    }

To use this extension simply place the jar file into your projects classpath an your ready to start. Alternatively when you using Maven as build tool add the following dependency to your pom.xml:

    <dependency>
        <groupId>de.mszturc</groupId>
        <artifactId>jsf-conditional-rendering-component</artifactId>
        <version>1.0</version>
        <type>jar</type>
    </dependency>
    
If you want to dive deeper into **requires** component, move on to the **full documentation**.