import { HttpInterceptor, HttpHandler, HttpRequest } from "@angular/common/http";
import { environment } from '../../../environments/environment';

export class BackEndInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler) {
        const url = request.url;

        if (url.startsWith('/portal/')) {
            request = request.clone({
                url: `${environment.portal.host}:${environment.portal.port}/${url}`
            });
        }

        return next.handle(request);
    }
}