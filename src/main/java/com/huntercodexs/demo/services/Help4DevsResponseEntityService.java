package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsResponseEntityService {

    private static ResponseEntity<?> continue100(Object bodySimulate) {

        String statusText = "100 Continue";
        Object bodyString = "This interim response indicates that the client should continue the request or " +
                "ignore the response if the request is already finished. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.CONTINUE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> switchingProtocols101(Object bodySimulate) {

        String statusText = "101 Switching Protocols";
        Object bodyString = "This code is sent in response to an Upgrade request header from the client and " +
                "indicates the protocol the server is switching to. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.SWITCHING_PROTOCOLS)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> processingWebdav102(Object bodySimulate) {

        String statusText = "102 Processing (WebDAV)";
        Object bodyString = "This code indicates that the server has received and is processing the request, " +
                "but no response is available yet. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PROCESSING)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> earlyHints103(Object bodySimulate) {

        String statusText = "103 Early Hints";
        Object bodyString = "This status code is primarily intended to be used with the Link header, " +
                "letting the user agent start preloading resources while the server prepares a response or " +
                "pre connect to an origin from which the page will need resources. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.TOO_EARLY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> ok200(Object bodySimulate) {

        String statusText = "200 OK";
        Object bodyString = "The request succeeded. The result meaning of 'success' depends on the HTTP method: "+
                "GET: The resource has been fetched and transmitted in the message body. " +
                "HEAD: The representation headers are included in the response without any message body. " +
                "PUT or POST: The resource describing the result of the action is transmitted in the message body. " +
                "TRACE: The message body contains the request message as received by the server. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> created201(Object bodySimulate) {

        String statusText = "201 Created";
        Object bodyString = "The request succeeded, and a new resource was created as a result. " +
                "This is typically the response sent after POST requests, or some PUT requests. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> accepted202(Object bodySimulate) {

        String statusText = "202 Accepted";
        Object bodyString = "The request has been received but not yet acted upon. It is noncommittal, " +
                "since there is no way in HTTP to later send an asynchronous response indicating the " +
                "outcome of the request. It is intended for cases where another process or server handles " +
                "the request, or for batch processing. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> nonAuthoritativeInformation203(Object bodySimulate) {

        String statusText = "203 Non-Authoritative Information";
        Object bodyString = "This response code means the returned metadata is not exactly the same " +
                "as is available from the origin server, but is collected from a local or a third-party " +
                "copy. This is mostly used for mirrors or backups of another resource. Except for " +
                "that specific case, the 200 OK response is preferred to this status. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> noContent204(Object bodySimulate) {

        String statusText = "204 No Content";
        Object bodyString = "There is no content to send for this request, but the headers may be useful. " +
                "The user agent may update its cached headers for this resource with the new ones. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> resetContent205(Object bodySimulate) {

        String statusText = "205 Reset Content";
        Object bodyString = "Tells the user agent to reset the document which sent this request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> partialContent206(Object bodySimulate) {

        String statusText = "206 Partial Content";
        Object bodyString = "This response code is used when the Range header is sent from the client " +
                "to request only part of a resource. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> multiStatus207(Object bodySimulate) {

        String statusText = "207 Multi-Status (WebDAV)";
        Object bodyString = "Conveys information about multiple resources, for situations " +
                "where multiple status codes might be appropriate. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.MULTI_STATUS)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> alreadyReported208(Object bodySimulate) {

        String statusText = "208 Already Reported (WebDAV)";
        Object bodyString = "Used inside a <dav:propstat> response element to avoid repeatedly enumerating the " +
                "internal members of multiple bindings to the same collection. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.ALREADY_REPORTED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> imUsed226(Object bodySimulate) {

        String statusText = "226 IM Used (HTTP Delta encoding)";
        Object bodyString = "The server has fulfilled a GET request for the resource, and the response is a " +
                "representation of the result of one or more instance-manipulations applied to the current instance. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.IM_USED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> multipleChoices300(Object bodySimulate) {

        String statusText = "300 Multiple Choices";
        Object bodyString = "The request has more than one possible response. The user agent or user should choose " +
                "one of them. (There is no standardized way of choosing one of the responses, but HTML links " +
                "to the possibilities are recommended so the user can pick.) " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.MULTIPLE_CHOICES)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> movedPermanently301(Object bodySimulate) {

        String statusText = "301 Moved Permanently";
        Object bodyString = "The URL of the requested resource has been changed permanently. " +
                "The new URL is given in the response. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> found302(Object bodySimulate) {

        String statusText = "302 Found";
        Object bodyString = "This response code means that the URI of requested resource has been " +
                "changed temporarily. Further changes in the URI might be made in the future. Therefore, " +
                "this same URI should be used by the client in future requests. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> seeOther303(Object bodySimulate) {

        String statusText = "303 See Other";
        Object bodyString = "The server sent this response to direct the client to get the requested " +
                "resource at another URI with a GET request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> notModified304(Object bodySimulate) {

        String statusText = "304 Not Modified";
        Object bodyString = "This is used for caching purposes. It tells the client that the response has " +
                "not been modified, so the client can continue to use the same cached version of the response. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NOT_MODIFIED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> useProxy305(Object bodySimulate) {

        String statusText = "305 Use Proxy Deprecated";
        Object bodyString = "Defined in a previous version of the HTTP specification to indicate that " +
                "a requested response must be accessed by a proxy. It has been deprecated due to " +
                "security concerns regarding in-band configuration of a proxy. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.USE_PROXY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> unUsed306(Object bodySimulate) {

        String statusText = "306 unused";
        Object bodyString = "This response code is no longer used; it is just reserved. It was " +
                "used in a previous version of the HTTP/1.1 specification. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.valueOf(306))
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> temporaryRedirect307(Object bodySimulate) {

        String statusText = "307 Temporary Redirect";
        Object bodyString = "The server sends this response to direct the client to get the requested resource at " +
                "another URI with the same method that was used in the prior request. This has the same " +
                "semantics as the 302 Found HTTP response code, with the exception that the user agent must " +
                "not change the HTTP method used: if a POST was used in the first request, a POST must be " +
                "used in the second request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.TEMPORARY_REDIRECT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> permanentRedirect308(Object bodySimulate) {

        String statusText = "308 Permanent Redirect";
        Object bodyString = "This means that the resource is now permanently located at another URI, specified by " +
                "the Location: HTTP Response header. This has the same semantics as the 301 Moved Permanently " +
                "HTTP response code, with the exception that the user agent must not change the HTTP method " +
                "used: if a POST was used in the first request, a POST must be used in the second request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> badRequest400(Object bodySimulate) {

        String statusText = "400 Bad Request";
        Object bodyString = "The server cannot or will not process the request due to something " +
                "that is perceived to be a client error (e.g., malformed request syntax, invalid request " +
                "message framing, or deceptive request routing). " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> unauthorized401(Object bodySimulate) {

        String statusText = "401 Unauthorized";
        Object bodyString = "Although the HTTP standard specifies 'unauthorized', semantically this " +
                "response means 'unauthenticated'. That is, the client must authenticate itself to " +
                "get the requested response. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> paymentRequired402(Object bodySimulate) {

        String statusText = "402 Payment Required Experimental";
        Object bodyString = "This response code is reserved for future use. The initial aim for creating " +
                "this code was using it for digital payment systems, however this status code is " +
                "used very rarely and no standard convention exists. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PAYMENT_REQUIRED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> forbidden403(Object bodySimulate) {

        String statusText = "403 Forbidden";
        Object bodyString = "The client does not have access rights to the content; that is, it is " +
                "unauthorized, so the server is refusing to give the requested resource. Unlike 401 " +
                "Unauthorized, the client's identity is known to the server. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> notFound404(Object bodySimulate) {

        String statusText = "404 Not Found";
        Object bodyString = "The server cannot find the requested resource. In the browser, this means the URL is not " +
                "recognized. In an API, this can also mean that the endpoint is valid but the resource itself " +
                "does not exist. Servers may also send this response instead of 403 Forbidden to hide the " +
                "existence of a resource from an unauthorized client. This response code is probably the " +
                "most well known due to its frequent occurrence on the web. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> methodNotAllowed405(Object bodySimulate) {

        String statusText = "405 Method Not Allowed";
        Object bodyString = "The request method is known by the server but is not supported by the target resource. " +
                "For example, an API may not allow calling DELETE to remove a resource. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> notAcceptable406(Object bodySimulate) {

        String statusText = "406 Not Acceptable";
        Object bodyString = "This response is sent when the web server, after performing server-driven content " +
                "negotiation, doesn't find any content that conforms to the criteria given by the user agent. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> proxyAuthenticationRequired407(Object bodySimulate) {

        String statusText = "407 Proxy Authentication Required";
        Object bodyString = "This is similar to 401 Unauthorized but authentication is needed to be done by a proxy. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PROXY_AUTHENTICATION_REQUIRED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> requestTimeout408(Object bodySimulate) {

        String statusText = "408 Request Timeout";
        Object bodyString = "This response is sent on an idle connection by some servers, even without any previous " +
                "request by the client. It means that the server would like to shut down this unused connection. " +
                "This response is used much more since some browsers, like Chrome, Firefox 27+, or IE9, use HTTP " +
                "pre-connection mechanisms to speed up surfing. Also note that some servers merely shut down the " +
                "connection without sending this message. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.REQUEST_TIMEOUT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> conflict409(Object bodySimulate) {

        String statusText = "409 Conflict";
        Object bodyString = "This response is sent when a request conflicts with the current state of the server. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> gone410(Object bodySimulate) {

        String statusText = "410 Gone";
        Object bodyString = "This response is sent when the requested content has been permanently deleted from server, " +
                "with no forwarding address. Clients are expected to remove their caches and links to the resource. " +
                "The HTTP specification intends this status code to be used for 'limited-time, promotional services'. " +
                "APIs should not feel compelled to indicate resources that have been deleted with this status code. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.GONE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> lengthRequired411(Object bodySimulate) {

        String statusText = "411 Length Required";
        Object bodyString = "Server rejected the request because the Content-Length header field " +
                "is not defined and the server requires it. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.LENGTH_REQUIRED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> preconditionFailed412(Object bodySimulate) {

        String statusText = "412 Precondition Failed";
        Object bodyString = "The client has indicated preconditions in its headers which the server does not meet. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> payloadTooLarge413(Object bodySimulate) {

        String statusText = "413 Payload Too Large";
        Object bodyString = "Request entity is larger than limits defined by server. The server might close the " +
                "connection or return an Retry-After header field. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> uriTooLong414(Object bodySimulate) {

        String statusText = "414 URI Too Long";
        Object bodyString = "The URI requested by the client is longer than the server is willing to interpret. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.URI_TOO_LONG)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> unsupportedMediaType415(Object bodySimulate) {

        String statusText = "415 Unsupported Media Type";
        Object bodyString = "The media format of the requested data is not supported by the server, " +
                "so the server is rejecting the request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> rangeNotSatisfiable416(Object bodySimulate) {

        String statusText = "416 Range Not Satisfiable";
        Object bodyString = "The range specified by the Range header field in the request cannot be fulfilled. It's " +
                "possible that the range is outside the size of the target URI's data. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> expectationFailed417(Object bodySimulate) {

        String statusText = "417 Expectation Failed";
        Object bodyString = "This response code means the expectation indicated by the Expect " +
                "request header field cannot be met by the server. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> iamTeapot418(Object bodySimulate) {

        String statusText = "418 I'm a teapot";
        Object bodyString = "The server refuses the attempt to brew coffee with a teapot. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> misDirectedRequest421(Object bodySimulate) {

        String statusText = "421 Misdirected Request";
        Object bodyString = "The request was directed at a server that is not able to produce a response. This can be " +
                "sent by a server that is not configured to produce responses for the combination of scheme and " +
                "authority that are included in the request URI. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.valueOf(421))
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> unprocessableContent422(Object bodySimulate) {

        String statusText = "422 Unprocessable Content (WebDAV)";
        Object bodyString = "The request was well-formed but was unable to be followed due to semantic errors. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> locked423(Object bodySimulate) {

        String statusText = "423 Locked (WebDAV)";
        Object bodyString = "The resource that is being accessed is locked. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.LOCKED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> failedDependence424(Object bodySimulate) {

        String statusText = "424 Failed Dependency (WebDAV)";
        Object bodyString = "The request failed due to failure of a previous request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.FAILED_DEPENDENCY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> tooEarly425(Object bodySimulate) {

        String statusText = "425 Too Early Experimental";
        Object bodyString = "Indicates that the server is unwilling to risk processing a request " +
                "that might be replayed. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.TOO_EARLY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> upgradeRequired426(Object bodySimulate) {

        String statusText = "426 Upgrade Required";
        Object bodyString = "The server refuses to perform the request using the current protocol but might be " +
                "willing to do so after the client upgrades to a different protocol. The server sends an " +
                "Upgrade header in a 426 response to indicate the required protocol(s). " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.UPGRADE_REQUIRED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> preconditionRequired428(Object bodySimulate) {

        String statusText = "428 Precondition Required";
        Object bodyString = "The origin server requires the request to be conditional. This response is intended to " +
                "prevent the 'lost update' problem, where a client GETs a resource's state, modifies it and PUTs " +
                "it back to the server, when meanwhile a third party has modified the state on the server, " +
                "leading to a conflict. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.PRECONDITION_REQUIRED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> tooManyRequests429(Object bodySimulate) {

        String statusText = "429 Too Many Requests";
        Object bodyString = "The user has sent too many requests in a given amount of time ('rate limiting'). " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.TOO_MANY_REQUESTS)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> requestHeaderFieldsTooLarge431(Object bodySimulate) {

        String statusText = "429 Too Many Requests";
        Object bodyString = "The user has sent too many requests in a given amount of time ('rate limiting'). " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> unavailableForLegalReasons451(Object bodySimulate) {

        String statusText = "451 Unavailable For Legal Reasons";
        Object bodyString = "The user agent requested a resource that cannot legally be provided, " +
                "such as a web page censored by a government. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> internalServerError500(Object bodySimulate) {

        String statusText = "500 Internal Server Error";
        Object bodyString = "The server has encountered a situation it does not know how to handle. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> notImplemented501(Object bodySimulate) {

        String statusText = "501 Not Implemented";
        Object bodyString = "The request method is not supported by the server and cannot be handled. The only methods " +
                "that servers are required to support (and therefore that must not return this code) are GET and HEAD. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> badGateway502(Object bodySimulate) {

        String statusText = "502 Bad Gateway";
        Object bodyString = "This error response means that the server, while working as a gateway to " +
                "get a response needed to handle the request, got an invalid response. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> serviceUnavailable503(Object bodySimulate) {

        String statusText = "503 Service Unavailable";
        Object bodyString = "The server is not ready to handle the request. Common causes are a server that is " +
                "down for maintenance or that is overloaded. Note that together with this response, a user-friendly " +
                "page explaining the problem should be sent. This response should be used for temporary conditions " +
                "and the Retry-After HTTP header should, if possible, contain the estimated time before the " +
                "recovery of the service. The webmaster must also take care about the caching-related headers " +
                "that are sent along with this response, as these temporary condition responses should " +
                "usually not be cached. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> gatewayTimeout504(Object bodySimulate) {

        String statusText = "504 Gateway Timeout";
        Object bodyString = "This error response is given when the server is acting as a gateway and cannot " +
                "get a response in time. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> httpVersionNotSupported505(Object bodySimulate) {

        String statusText = "505 HTTP Version Not Supported";
        Object bodyString = "The HTTP version used in the request is not supported by the server. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> variantAlsoNegotiates506(Object bodySimulate) {

        String statusText = "506 Variant Also Negotiates";
        Object bodyString = "The server has an internal configuration error: the chosen variant resource is " +
                "configured to engage in transparent content negotiation itself, and is therefore not a " +
                "proper end point in the negotiation process. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.VARIANT_ALSO_NEGOTIATES)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> insufficientStorage507(Object bodySimulate) {

        String statusText = "507 Insufficient Storage (WebDAV)";
        Object bodyString = "The method could not be performed on the resource because the server is " +
                "unable to store the representation needed to successfully complete the request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> loopDetected508(Object bodySimulate) {

        String statusText = "508 Loop Detected (WebDAV)";
        Object bodyString = "The server detected an infinite loop while processing the request. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.LOOP_DETECTED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> notExtended510(Object bodySimulate) {

        String statusText = "510 Not Extended";
        Object bodyString = "Further extensions to the request are required for the server to fulfill it. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NOT_EXTENDED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    private static ResponseEntity<?> networkAuthenticationRequired511(Object bodySimulate) {

        String statusText = "511 Network Authentication Required";
        Object bodyString = "Indicates that the client needs to authenticate to gain network access. " +
                "[Mozilla MDN]";

        if (bodySimulate != null) {
            bodyString = bodySimulate;
        }

        return ResponseEntity
                .status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
                .headers(new HttpHeaders())
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyString);
    }

    public static ResponseEntity<?> responseEntitySimulate(int httpCode, Object bodySimulate) {

        if (httpCode == 100) {
            return continue100(bodySimulate);
        }

        if (httpCode == 101) {
            return switchingProtocols101(bodySimulate);
        }

        if (httpCode == 102) {
            return processingWebdav102(bodySimulate);
        }

        if (httpCode == 103) {
            return earlyHints103(bodySimulate);
        }

        if (httpCode == 200) {
            return ok200(bodySimulate);
        }

        if (httpCode == 201) {
            return created201(bodySimulate);
        }

        if (httpCode == 202) {
            return accepted202(bodySimulate);
        }

        if (httpCode == 203) {
            return nonAuthoritativeInformation203(bodySimulate);
        }

        if (httpCode == 204) {
            return noContent204(bodySimulate);
        }

        if (httpCode == 205) {
            return resetContent205(bodySimulate);
        }

        if (httpCode == 206) {
            return partialContent206(bodySimulate);
        }

        if (httpCode == 207) {
            return multiStatus207(bodySimulate);
        }

        if (httpCode == 208) {
            return alreadyReported208(bodySimulate);
        }

        if (httpCode == 226) {
            return imUsed226(bodySimulate);
        }

        if (httpCode == 300) {
            return multipleChoices300(bodySimulate);
        }

        if (httpCode == 301) {
            return movedPermanently301(bodySimulate);
        }

        if (httpCode == 302) {
            return found302(bodySimulate);
        }

        if (httpCode == 303) {
            return seeOther303(bodySimulate);
        }

        if (httpCode == 304) {
            return notModified304(bodySimulate);
        }

        if (httpCode == 305) {
            return useProxy305(bodySimulate);
        }

        if (httpCode == 306) {
            return unUsed306(bodySimulate);
        }

        if (httpCode == 307) {
            return temporaryRedirect307(bodySimulate);
        }

        if (httpCode == 308) {
            return permanentRedirect308(bodySimulate);
        }

        if (httpCode == 400) {
            return badRequest400(bodySimulate);
        }

        if (httpCode == 401) {
            return unauthorized401(bodySimulate);
        }

        if (httpCode == 402) {
            return paymentRequired402(bodySimulate);
        }

        if (httpCode == 403) {
            return forbidden403(bodySimulate);
        }

        if (httpCode == 404) {
            return notFound404(bodySimulate);
        }

        if (httpCode == 405) {
            return methodNotAllowed405(bodySimulate);
        }

        if (httpCode == 406) {
            return notAcceptable406(bodySimulate);
        }

        if (httpCode == 407) {
            return proxyAuthenticationRequired407(bodySimulate);
        }

        if (httpCode == 408) {
            return requestTimeout408(bodySimulate);
        }

        if (httpCode == 409) {
            return conflict409(bodySimulate);
        }

        if (httpCode == 410) {
            return gone410(bodySimulate);
        }

        if (httpCode == 411) {
            return lengthRequired411(bodySimulate);
        }

        if (httpCode == 412) {
            return preconditionFailed412(bodySimulate);
        }

        if (httpCode == 413) {
            return payloadTooLarge413(bodySimulate);
        }

        if (httpCode == 414) {
            return uriTooLong414(bodySimulate);
        }

        if (httpCode == 415) {
            return unsupportedMediaType415(bodySimulate);
        }

        if (httpCode == 416) {
            return rangeNotSatisfiable416(bodySimulate);
        }

        if (httpCode == 417) {
            return expectationFailed417(bodySimulate);
        }

        if (httpCode == 418) {
            return iamTeapot418(bodySimulate);
        }

        if (httpCode == 421) {
            return misDirectedRequest421(bodySimulate);
        }

        if (httpCode == 422) {
            return unprocessableContent422(bodySimulate);
        }

        if (httpCode == 423) {
            return locked423(bodySimulate);
        }

        if (httpCode == 424) {
            return failedDependence424(bodySimulate);
        }

        if (httpCode == 425) {
            return tooEarly425(bodySimulate);
        }

        if (httpCode == 426) {
            return upgradeRequired426(bodySimulate);
        }

        if (httpCode == 428) {
            return preconditionRequired428(bodySimulate);
        }

        if (httpCode == 429) {
            return tooManyRequests429(bodySimulate);
        }

        if (httpCode == 431) {
            return requestHeaderFieldsTooLarge431(bodySimulate);
        }

        if (httpCode == 451) {
            return unavailableForLegalReasons451(bodySimulate);
        }

        if (httpCode == 500) {
            return internalServerError500(bodySimulate);
        }

        if (httpCode == 501) {
            return notImplemented501(bodySimulate);
        }

        if (httpCode == 502) {
            return badGateway502(bodySimulate);
        }

        if (httpCode == 503) {
            return serviceUnavailable503(bodySimulate);
        }

        if (httpCode == 504) {
            return gatewayTimeout504(bodySimulate);
        }

        if (httpCode == 505) {
            return httpVersionNotSupported505(bodySimulate);
        }

        if (httpCode == 506) {
            return variantAlsoNegotiates506(bodySimulate);
        }

        if (httpCode == 507) {
            return insufficientStorage507(bodySimulate);
        }

        if (httpCode == 508) {
            return loopDetected508(bodySimulate);
        }

        if (httpCode == 510) {
            return notExtended510(bodySimulate);
        }

        if (httpCode == 511) {
            return networkAuthenticationRequired511(bodySimulate);
        }

        throw new RuntimeException("[responseEntitySimulate] Wrong/Unknown HTTP Code");
    }

}
