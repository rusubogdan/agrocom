package com.agrocom.config;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * <code>Apache tiles configuration class. Implements DefinitionsFactory to provide programmatic
 * configuration for Apache tiles.</code>
 */
public final class TilesDefinitionsConfig implements DefinitionsFactory {

    private static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
    private static final Attribute AUTH_USER_TEMPLATE = new Attribute("/WEB-INF/views/template/authUserTemplate.jsp");
    private static final Attribute ANON_USER_TEMPLATE = new Attribute("/WEB-INF/views/template/anonUserTemplate.jsp");

    @Override
    public Definition getDefinition(String name, Request tilesContext) {
        return tilesDefinitions.get(name);
    }

    /**
     * @param name  <code>Name of the view</code>
     * @param title <code>Page title</code>
     * @param body  <code>Body JSP file path</code>
     *              <p>
     *              <code>Adds default layout definitions</code>
     */
    private static void addAuthUserLayoutDef(String name, String title, String body) {
        Map<String, Attribute> attributes = new HashMap<>();

        attributes.put("title", new Attribute(title));
        attributes.put("header", new Attribute("/WEB-INF/views/template/header.jsp"));
        attributes.put("menu", new Attribute("/WEB-INF/views/template/menu.jsp"));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute("/WEB-INF/views/template/footer.jsp"));

        tilesDefinitions.put(name, new Definition(name, AUTH_USER_TEMPLATE, attributes));
    }

    private static void addAuthNoMenuLayoutDef(String name, String title, String body) {
        Map<String, Attribute> attributes = new HashMap<>();

        attributes.put("title", new Attribute(title));
        attributes.put("header", new Attribute("/WEB-INF/views/template/header.jsp"));
        attributes.put("menu", new Attribute("/WEB-INF/views/template/void.jsp"));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute("/WEB-INF/views/template/footer.jsp"));

        tilesDefinitions.put(name, new Definition(name, AUTH_USER_TEMPLATE, attributes));
    }

    private static void addAnonUserLayoutDef(String name, String title, String body) {
        Map<String, Attribute> attributes = new HashMap<>();

        attributes.put("title", new Attribute(title));
        attributes.put("header", new Attribute("/WEB-INF/views/template/header.jsp"));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute("/WEB-INF/views/template/footer.jsp"));

        tilesDefinitions.put(name, new Definition(name, ANON_USER_TEMPLATE, attributes));
    }

    private static void addErrorLayoutDef(String name, String title, String body) {
        Map<String, Attribute> attributes = new HashMap<>();

        attributes.put("title", new Attribute(title));
        attributes.put("header", new Attribute("/WEB-INF/views/template/void.jsp"));
        attributes.put("menu", new Attribute("/WEB-INF/views/template/void.jsp"));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute("/WEB-INF/views/template/void.jsp"));

        tilesDefinitions.put(name, new Definition(name, ANON_USER_TEMPLATE, attributes));
    }

    /**
     * <code>Add Apache tiles definitions</code>
     */
    public static void addDefinitions() {
        addAuthUserLayoutDef("home", "Home", "/WEB-INF/views/home.jsp");
        addAuthUserLayoutDef("addSociety", "Add Society", "/WEB-INF/views/addSociety.jsp");
        addAuthUserLayoutDef("addEmployeePage", "Add Employee", "/WEB-INF/views/addEmployeePage.jsp");
        addAuthUserLayoutDef("viewEmployee", "View Employee", "/WEB-INF/views/viewEmployee.jsp");
        addAuthUserLayoutDef("editEmployee", "Edit Employee", "/WEB-INF/views/editEmployeePage.jsp");
        addAuthUserLayoutDef("addInfield", "Add Infield", "/WEB-INF/views/addInfield.jsp");
        addAuthUserLayoutDef("viewInfield", "View infield", "/WEB-INF/views/viewInfield.jsp");
        addAuthUserLayoutDef("editInfield", "Edit Infield", "/WEB-INF/views/editInfieldPage.jsp");

        addAuthNoMenuLayoutDef("societies", "Societies", "/WEB-INF/views/societies.jsp");
        addAuthNoMenuLayoutDef("announcements", "announcements", "/WEB-INF/views/announcements.jsp");

        addAnonUserLayoutDef("firstPage", "First Page", "/WEB-INF/views/firstPage.jsp");
        addAnonUserLayoutDef("login", "Login/Register", "/WEB-INF/views/login.jsp");
        addAnonUserLayoutDef("publicPage", "public", "/WEB-INF/views/publicPage.jsp");

        addErrorLayoutDef("500", "500", "/WEB-INF/views/500.jsp");
        addErrorLayoutDef("404", "404", "/WEB-INF/views/404.jsp");
    }
}