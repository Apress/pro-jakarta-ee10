package this.package;

import jakarta.faces.component.FacesComponent;
import jakarta.faces.component.UIComponent;

@FacesComponent(createTag = true, 
        namespace = "https://mycompany.com/mynamespace",
        tagName = "custom-component", 
        value = "this.package.CustomComponent")
public class CustomInput extends UIComponent {
    ...
}
