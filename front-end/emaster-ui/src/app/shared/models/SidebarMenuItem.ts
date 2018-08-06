export class SidebarMenuItem {
    displayName: string;
    route: string;
    iconName?: string;
    disabled?: boolean;
    children?: SidebarMenuItem[];

    constructor(displayName: string, route: string, 
        iconName?: string, disabled?: boolean, children?: SidebarMenuItem[]) {
        this.displayName = displayName;
        this.route = route;
        this.iconName = iconName ? iconName : 'folder';
        this.disabled = disabled ? disabled : false;
        this.children = children;
    }
}