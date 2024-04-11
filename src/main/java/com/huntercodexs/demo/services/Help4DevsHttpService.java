package com.huntercodexs.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class Help4DevsHttpService {

    private static HttpClientErrorException continue100(Object responseSimulate) {

        String statusText = "100 Continue";
        byte[] bodyString = ("This interim response indicates that the client should continue the request or " +
                "ignore the response if the request is already finished. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.CONTINUE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException switchingProtocols101(Object responseSimulate) {

        String statusText = "101 Switching Protocols";
        byte[] bodyString = ("This code is sent in response to an Upgrade request header from the client and " +
                "indicates the protocol the server is switching to. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.SWITCHING_PROTOCOLS,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException processingWebdav102(Object responseSimulate) {

        String statusText = "102 Processing (WebDAV)";
        byte[] bodyString = ("This code indicates that the server has received and is processing the request, " +
                "but no response is available yet. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PROCESSING,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException earlyHints103(Object responseSimulate) {

        String statusText = "103 Early Hints";
        byte[] bodyString = ("This status code is primarily intended to be used with the Link header, " +
                "letting the user agent start preloading resources while the server prepares a response or " +
                "pre connect to an origin from which the page will need resources. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.TOO_EARLY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException ok200(Object responseSimulate) {

        String statusText = "200 OK";
        byte[] bodyString = ("The request succeeded. The result meaning of 'success' depends on the HTTP method: "+
                "GET: The resource has been fetched and transmitted in the message body. " +
                "HEAD: The representation headers are included in the response without any message body. " +
                "PUT or POST: The resource describing the result of the action is transmitted in the message body. " +
                "TRACE: The message body contains the request message as received by the server. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.OK,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException created201(Object responseSimulate) {

        String statusText = "201 Created";
        byte[] bodyString = ("The request succeeded, and a new resource was created as a result. " +
                "This is typically the response sent after POST requests, or some PUT requests. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.CREATED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException accepted202(Object responseSimulate) {

        String statusText = "202 Accepted";
        byte[] bodyString = ("The request has been received but not yet acted upon. It is noncommittal, " +
                "since there is no way in HTTP to later send an asynchronous response indicating the " +
                "outcome of the request. It is intended for cases where another process or server handles " +
                "the request, or for batch processing. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.ACCEPTED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException nonAuthoritativeInformation203(Object responseSimulate) {

        String statusText = "203 Non-Authoritative Information";
        byte[] bodyString = ("This response code means the returned metadata is not exactly the same " +
                "as is available from the origin server, but is collected from a local or a third-party " +
                "copy. This is mostly used for mirrors or backups of another resource. Except for " +
                "that specific case, the 200 OK response is preferred to this status. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NON_AUTHORITATIVE_INFORMATION,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException noContent204(Object responseSimulate) {

        String statusText = "204 No Content";
        byte[] bodyString = ("There is no content to send for this request, but the headers may be useful. " +
                "The user agent may update its cached headers for this resource with the new ones. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NO_CONTENT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException resetContent205(Object responseSimulate) {

        String statusText = "205 Reset Content";
        byte[] bodyString = ("Tells the user agent to reset the document which sent this request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.BAD_REQUEST,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException partialContent206(Object responseSimulate) {

        String statusText = "206 Partial Content";
        byte[] bodyString = ("This response code is used when the Range header is sent from the client " +
                "to request only part of a resource. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PARTIAL_CONTENT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException multiStatus207(Object responseSimulate) {

        String statusText = "207 Multi-Status (WebDAV)";
        byte[] bodyString = ("Conveys information about multiple resources, for situations " +
                "where multiple status codes might be appropriate. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.MULTI_STATUS,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException alreadyReported208(Object responseSimulate) {

        String statusText = "208 Already Reported (WebDAV)";
        byte[] bodyString = ("Used inside a <dav:propstat> response element to avoid repeatedly enumerating the " +
                "internal members of multiple bindings to the same collection. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.ALREADY_REPORTED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException imUsed226(Object responseSimulate) {

        String statusText = "226 IM Used (HTTP Delta encoding)";
        byte[] bodyString = ("The server has fulfilled a GET request for the resource, and the response is a " +
                "representation of the result of one or more instance-manipulations applied to the current instance. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.IM_USED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException multipleChoices300(Object responseSimulate) {

        String statusText = "300 Multiple Choices";
        byte[] bodyString = ("The request has more than one possible response. The user agent or user should choose " +
                "one of them. (There is no standardized way of choosing one of the responses, but HTML links " +
                "to the possibilities are recommended so the user can pick.) " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.MULTIPLE_CHOICES,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException movedPermanently301(Object responseSimulate) {

        String statusText = "301 Moved Permanently";
        byte[] bodyString = ("The URL of the requested resource has been changed permanently. " +
                "The new URL is given in the response. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.MOVED_PERMANENTLY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException found302(Object responseSimulate) {

        String statusText = "302 Found";
        byte[] bodyString = ("This response code means that the URI of requested resource has been " +
                "changed temporarily. Further changes in the URI might be made in the future. Therefore, " +
                "this same URI should be used by the client in future requests. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.FOUND,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException seeOther303(Object responseSimulate) {

        String statusText = "303 See Other";
        byte[] bodyString = ("The server sent this response to direct the client to get the requested " +
                "resource at another URI with a GET request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.SEE_OTHER,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException notModified304(Object responseSimulate) {

        String statusText = "304 Not Modified";
        byte[] bodyString = ("This is used for caching purposes. It tells the client that the response has " +
                "not been modified, so the client can continue to use the same cached version of the response. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NOT_MODIFIED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException useProxy305(Object responseSimulate) {

        String statusText = "305 Use Proxy Deprecated";
        byte[] bodyString = ("Defined in a previous version of the HTTP specification to indicate that " +
                "a requested response must be accessed by a proxy. It has been deprecated due to " +
                "security concerns regarding in-band configuration of a proxy. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.USE_PROXY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException unUsed306(Object responseSimulate) {

        String statusText = "306 unused";
        byte[] bodyString = ("This response code is no longer used; it is just reserved. It was " +
                "used in a previous version of the HTTP/1.1 specification. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.valueOf(306),
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException temporaryRedirect307(Object responseSimulate) {

        String statusText = "307 Temporary Redirect";
        byte[] bodyString = ("The server sends this response to direct the client to get the requested resource at " +
                "another URI with the same method that was used in the prior request. This has the same " +
                "semantics as the 302 Found HTTP response code, with the exception that the user agent must " +
                "not change the HTTP method used: if a POST was used in the first request, a POST must be " +
                "used in the second request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.TEMPORARY_REDIRECT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException permanentRedirect308(Object responseSimulate) {

        String statusText = "308 Permanent Redirect";
        byte[] bodyString = ("This means that the resource is now permanently located at another URI, specified by " +
                "the Location: HTTP Response header. This has the same semantics as the 301 Moved Permanently " +
                "HTTP response code, with the exception that the user agent must not change the HTTP method " +
                "used: if a POST was used in the first request, a POST must be used in the second request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PERMANENT_REDIRECT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException badRequest400(Object responseSimulate) {

        String statusText = "400 Bad Request";
        byte[] bodyString = ("The server cannot or will not process the request due to something " +
                "that is perceived to be a client error (e.g., malformed request syntax, invalid request " +
                "message framing, or deceptive request routing). " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.BAD_REQUEST,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException unauthorized401(Object responseSimulate) {

        String statusText = "401 Unauthorized";
        byte[] bodyString = ("Although the HTTP standard specifies 'unauthorized', semantically this " +
                "response means 'unauthenticated'. That is, the client must authenticate itself to " +
                "get the requested response. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.UNAUTHORIZED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException paymentRequired402(Object responseSimulate) {

        String statusText = "402 Payment Required Experimental";
        byte[] bodyString = ("This response code is reserved for future use. The initial aim for creating " +
                "this code was using it for digital payment systems, however this status code is " +
                "used very rarely and no standard convention exists. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PAYMENT_REQUIRED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException forbidden403(Object responseSimulate) {

        String statusText = "403 Forbidden";
        byte[] bodyString = ("The client does not have access rights to the content; that is, it is " +
                "unauthorized, so the server is refusing to give the requested resource. Unlike 401 " +
                "Unauthorized, the client's identity is known to the server. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.FORBIDDEN,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException notFound404(Object responseSimulate) {

        String statusText = "404 Not Found";
        byte[] bodyString = ("The server cannot find the requested resource. In the browser, this means the URL is not " +
                "recognized. In an API, this can also mean that the endpoint is valid but the resource itself " +
                "does not exist. Servers may also send this response instead of 403 Forbidden to hide the " +
                "existence of a resource from an unauthorized client. This response code is probably the " +
                "most well known due to its frequent occurrence on the web. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NOT_FOUND,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException methodNotAllowed405(Object responseSimulate) {

        String statusText = "405 Method Not Allowed";
        byte[] bodyString = ("The request method is known by the server but is not supported by the target resource. " +
                "For example, an API may not allow calling DELETE to remove a resource. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.METHOD_NOT_ALLOWED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException notAcceptable406(Object responseSimulate) {

        String statusText = "406 Not Acceptable";
        byte[] bodyString = ("This response is sent when the web server, after performing server-driven content " +
                "negotiation, doesn't find any content that conforms to the criteria given by the user agent. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NOT_ACCEPTABLE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException proxyAuthenticationRequired407(Object responseSimulate) {

        String statusText = "407 Proxy Authentication Required";
        byte[] bodyString = ("This is similar to 401 Unauthorized but authentication is needed to be done by a proxy. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PROXY_AUTHENTICATION_REQUIRED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException requestTimeout408(Object responseSimulate) {

        String statusText = "408 Request Timeout";
        byte[] bodyString = ("This response is sent on an idle connection by some servers, even without any previous " +
                "request by the client. It means that the server would like to shut down this unused connection. " +
                "This response is used much more since some browsers, like Chrome, Firefox 27+, or IE9, use HTTP " +
                "pre-connection mechanisms to speed up surfing. Also note that some servers merely shut down the " +
                "connection without sending this message. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.REQUEST_TIMEOUT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException conflict409(Object responseSimulate) {

        String statusText = "409 Conflict";
        byte[] bodyString = ("This response is sent when a request conflicts with the current state of the server. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.CONFLICT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException gone410(Object responseSimulate) {

        String statusText = "410 Gone";
        byte[] bodyString = ("This response is sent when the requested content has been permanently deleted from server, " +
                "with no forwarding address. Clients are expected to remove their caches and links to the resource. " +
                "The HTTP specification intends this status code to be used for 'limited-time, promotional services'. " +
                "APIs should not feel compelled to indicate resources that have been deleted with this status code. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.GONE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException lengthRequired411(Object responseSimulate) {

        String statusText = "411 Length Required";
        byte[] bodyString = ("Server rejected the request because the Content-Length header field " +
                "is not defined and the server requires it. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.LENGTH_REQUIRED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException preconditionFailed412(Object responseSimulate) {

        String statusText = "412 Precondition Failed";
        byte[] bodyString = ("The client has indicated preconditions in its headers which the server does not meet. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PRECONDITION_FAILED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException payloadTooLarge413(Object responseSimulate) {

        String statusText = "413 Payload Too Large";
        byte[] bodyString = ("Request entity is larger than limits defined by server. The server might close the " +
                "connection or return an Retry-After header field. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PAYLOAD_TOO_LARGE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException uriTooLong414(Object responseSimulate) {

        String statusText = "414 URI Too Long";
        byte[] bodyString = ("The URI requested by the client is longer than the server is willing to interpret. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.URI_TOO_LONG,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException unsupportedMediaType415(Object responseSimulate) {

        String statusText = "415 Unsupported Media Type";
        byte[] bodyString = ("The media format of the requested data is not supported by the server, " +
                "so the server is rejecting the request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException rangeNotSatisfiable416(Object responseSimulate) {

        String statusText = "416 Range Not Satisfiable";
        byte[] bodyString = ("The range specified by the Range header field in the request cannot be fulfilled. It's " +
                "possible that the range is outside the size of the target URI's data. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException expectationFailed417(Object responseSimulate) {

        String statusText = "417 Expectation Failed";
        byte[] bodyString = ("This response code means the expectation indicated by the Expect " +
                "request header field cannot be met by the server. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.EXPECTATION_FAILED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException iamTeapot418(Object responseSimulate) {

        String statusText = "418 I'm a teapot";
        byte[] bodyString = ("The server refuses the attempt to brew coffee with a teapot. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.I_AM_A_TEAPOT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException misDirectedRequest421(Object responseSimulate) {

        String statusText = "421 Misdirected Request";
        byte[] bodyString = ("The request was directed at a server that is not able to produce a response. This can be " +
                "sent by a server that is not configured to produce responses for the combination of scheme and " +
                "authority that are included in the request URI. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.valueOf(421),
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException unprocessableContent422(Object responseSimulate) {

        String statusText = "422 Unprocessable Content (WebDAV)";
        byte[] bodyString = ("The request was well-formed but was unable to be followed due to semantic errors. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.UNPROCESSABLE_ENTITY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException locked423(Object responseSimulate) {

        String statusText = "423 Locked (WebDAV)";
        byte[] bodyString = ("The resource that is being accessed is locked. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.LOCKED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException failedDependence424(Object responseSimulate) {

        String statusText = "424 Failed Dependency (WebDAV)";
        byte[] bodyString = ("The request failed due to failure of a previous request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.FAILED_DEPENDENCY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException tooEarly425(Object responseSimulate) {

        String statusText = "425 Too Early Experimental";
        byte[] bodyString = ("Indicates that the server is unwilling to risk processing a request " +
                "that might be replayed. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.TOO_EARLY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException upgradeRequired426(Object responseSimulate) {

        String statusText = "426 Upgrade Required";
        byte[] bodyString = ("The server refuses to perform the request using the current protocol but might be " +
                "willing to do so after the client upgrades to a different protocol. The server sends an " +
                "Upgrade header in a 426 response to indicate the required protocol(s). " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.UPGRADE_REQUIRED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException preconditionRequired428(Object responseSimulate) {

        String statusText = "428 Precondition Required";
        byte[] bodyString = ("The origin server requires the request to be conditional. This response is intended to " +
                "prevent the 'lost update' problem, where a client GETs a resource's state, modifies it and PUTs " +
                "it back to the server, when meanwhile a third party has modified the state on the server, " +
                "leading to a conflict. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.PRECONDITION_REQUIRED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException tooManyRequests429(Object responseSimulate) {

        String statusText = "429 Too Many Requests";
        byte[] bodyString = ("The user has sent too many requests in a given amount of time ('rate limiting'). " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.TOO_MANY_REQUESTS,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException requestHeaderFieldsTooLarge431(Object responseSimulate) {

        String statusText = "429 Too Many Requests";
        byte[] bodyString = ("The user has sent too many requests in a given amount of time ('rate limiting'). " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException unavailableForLegalReasons451(Object responseSimulate) {

        String statusText = "451 Unavailable For Legal Reasons";
        byte[] bodyString = ("The user agent requested a resource that cannot legally be provided, " +
                "such as a web page censored by a government. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException internalServerError500(Object responseSimulate) {

        String statusText = "500 Internal Server Error";
        byte[] bodyString = ("The server has encountered a situation it does not know how to handle. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.INTERNAL_SERVER_ERROR,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException notImplemented501(Object responseSimulate) {

        String statusText = "501 Not Implemented";
        byte[] bodyString = ("The request method is not supported by the server and cannot be handled. The only methods " +
                "that servers are required to support (and therefore that must not return this code) are GET and HEAD. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NOT_IMPLEMENTED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException badGateway502(Object responseSimulate) {

        String statusText = "502 Bad Gateway";
        byte[] bodyString = ("This error response means that the server, while working as a gateway to " +
                "get a response needed to handle the request, got an invalid response. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.BAD_GATEWAY,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException serviceUnavailable503(Object responseSimulate) {

        String statusText = "503 Service Unavailable";
        byte[] bodyString = ("The server is not ready to handle the request. Common causes are a server that is " +
                "down for maintenance or that is overloaded. Note that together with this response, a user-friendly " +
                "page explaining the problem should be sent. This response should be used for temporary conditions " +
                "and the Retry-After HTTP header should, if possible, contain the estimated time before the " +
                "recovery of the service. The webmaster must also take care about the caching-related headers " +
                "that are sent along with this response, as these temporary condition responses should " +
                "usually not be cached. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.SERVICE_UNAVAILABLE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException gatewayTimeout504(Object responseSimulate) {

        String statusText = "504 Gateway Timeout";
        byte[] bodyString = ("This error response is given when the server is acting as a gateway and cannot " +
                "get a response in time. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.GATEWAY_TIMEOUT,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException httpVersionNotSupported505(Object responseSimulate) {

        String statusText = "505 HTTP Version Not Supported";
        byte[] bodyString = ("The HTTP version used in the request is not supported by the server. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.HTTP_VERSION_NOT_SUPPORTED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException variantAlsoNegotiates506(Object responseSimulate) {

        String statusText = "506 Variant Also Negotiates";
        byte[] bodyString = ("The server has an internal configuration error: the chosen variant resource is " +
                "configured to engage in transparent content negotiation itself, and is therefore not a " +
                "proper end point in the negotiation process. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.VARIANT_ALSO_NEGOTIATES,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException insufficientStorage507(Object responseSimulate) {

        String statusText = "507 Insufficient Storage (WebDAV)";
        byte[] bodyString = ("The method could not be performed on the resource because the server is " +
                "unable to store the representation needed to successfully complete the request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.INSUFFICIENT_STORAGE,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException loopDetected508(Object responseSimulate) {

        String statusText = "508 Loop Detected (WebDAV)";
        byte[] bodyString = ("The server detected an infinite loop while processing the request. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.LOOP_DETECTED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException notExtended510(Object responseSimulate) {

        String statusText = "510 Not Extended";
        byte[] bodyString = ("Further extensions to the request are required for the server to fulfill it. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NOT_EXTENDED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    private static HttpClientErrorException networkAuthenticationRequired511(Object responseSimulate) {

        String statusText = "511 Network Authentication Required";
        byte[] bodyString = ("Indicates that the client needs to authenticate to gain network access. " +
                "[Mozilla MDN]").getBytes(StandardCharsets.UTF_8);

        if (responseSimulate != null) {
            bodyString = responseSimulate.toString().getBytes(StandardCharsets.UTF_8);
        }

        return HttpClientErrorException.create(
                HttpStatus.NETWORK_AUTHENTICATION_REQUIRED,
                statusText,
                new HttpHeaders(),
                bodyString,
                Charset.defaultCharset());
    }

    public static HttpClientErrorException restResponseSimulate(
            int httpCode,
            boolean throwable,
            Object responseSimulate
    ) {

        if (httpCode == 100) {
            if (throwable) {
                throw continue100(responseSimulate);
            }
            return continue100(responseSimulate);
        }

        if (httpCode == 101) {
            if (throwable) {
                throw switchingProtocols101(responseSimulate);
            }
            return switchingProtocols101(responseSimulate);
        }

        if (httpCode == 102) {
            if (throwable) {
                throw processingWebdav102(responseSimulate);
            }
            return processingWebdav102(responseSimulate);
        }

        if (httpCode == 103) {
            if (throwable) {
                throw earlyHints103(responseSimulate);
            }
            return earlyHints103(responseSimulate);
        }

        if (httpCode == 200) {
            if (throwable) {
                throw ok200(responseSimulate);
            }
            return ok200(responseSimulate);
        }

        if (httpCode == 201) {
            if (throwable) {
                throw created201(responseSimulate);
            }
            return created201(responseSimulate);
        }

        if (httpCode == 202) {
            if (throwable) {
                throw accepted202(responseSimulate);
            }
            return accepted202(responseSimulate);
        }

        if (httpCode == 203) {
            if (throwable) {
                throw nonAuthoritativeInformation203(responseSimulate);
            }
            return nonAuthoritativeInformation203(responseSimulate);
        }

        if (httpCode == 204) {
            if (throwable) {
                throw noContent204(responseSimulate);
            }
            return noContent204(responseSimulate);
        }

        if (httpCode == 205) {
            if (throwable) {
                throw resetContent205(responseSimulate);
            }
            return resetContent205(responseSimulate);
        }

        if (httpCode == 206) {
            if (throwable) {
                throw partialContent206(responseSimulate);
            }
            return partialContent206(responseSimulate);
        }

        if (httpCode == 207) {
            if (throwable) {
                throw multiStatus207(responseSimulate);
            }
            return multiStatus207(responseSimulate);
        }

        if (httpCode == 208) {
            if (throwable) {
                throw alreadyReported208(responseSimulate);
            }
            return alreadyReported208(responseSimulate);
        }

        if (httpCode == 226) {
            if (throwable) {
                throw imUsed226(responseSimulate);
            }
            return imUsed226(responseSimulate);
        }

        if (httpCode == 300) {
            if (throwable) {
                throw multipleChoices300(responseSimulate);
            }
            return multipleChoices300(responseSimulate);
        }

        if (httpCode == 301) {
            if (throwable) {
                throw movedPermanently301(responseSimulate);
            }
            return movedPermanently301(responseSimulate);
        }

        if (httpCode == 302) {
            if (throwable) {
                throw found302(responseSimulate);
            }
            return found302(responseSimulate);
        }

        if (httpCode == 303) {
            if (throwable) {
                throw seeOther303(responseSimulate);
            }
            return seeOther303(responseSimulate);
        }

        if (httpCode == 304) {
            if (throwable) {
                throw notModified304(responseSimulate);
            }
            return notModified304(responseSimulate);
        }

        if (httpCode == 305) {
            if (throwable) {
                throw useProxy305(responseSimulate);
            }
            return useProxy305(responseSimulate);
        }

        if (httpCode == 306) {
            if (throwable) {
                throw unUsed306(responseSimulate);
            }
            return unUsed306(responseSimulate);
        }

        if (httpCode == 307) {
            if (throwable) {
                throw temporaryRedirect307(responseSimulate);
            }
            return temporaryRedirect307(responseSimulate);
        }

        if (httpCode == 308) {
            if (throwable) {
                throw permanentRedirect308(responseSimulate);
            }
            return permanentRedirect308(responseSimulate);
        }

        if (httpCode == 400) {
            if (throwable) {
                throw badRequest400(responseSimulate);
            }
            return badRequest400(responseSimulate);
        }

        if (httpCode == 401) {
            if (throwable) {
                throw unauthorized401(responseSimulate);
            }
            return unauthorized401(responseSimulate);
        }

        if (httpCode == 402) {
            if (throwable) {
                throw paymentRequired402(responseSimulate);
            }
            return paymentRequired402(responseSimulate);
        }

        if (httpCode == 403) {
            if (throwable) {
                throw forbidden403(responseSimulate);
            }
            return forbidden403(responseSimulate);
        }

        if (httpCode == 404) {
            if (throwable) {
                throw notFound404(responseSimulate);
            }
            return notFound404(responseSimulate);
        }

        if (httpCode == 405) {
            if (throwable) {
                throw methodNotAllowed405(responseSimulate);
            }
            return methodNotAllowed405(responseSimulate);
        }

        if (httpCode == 406) {
            if (throwable) {
                throw notAcceptable406(responseSimulate);
            }
            return notAcceptable406(responseSimulate);
        }

        if (httpCode == 407) {
            if (throwable) {
                throw proxyAuthenticationRequired407(responseSimulate);
            }
            return proxyAuthenticationRequired407(responseSimulate);
        }

        if (httpCode == 408) {
            if (throwable) {
                throw requestTimeout408(responseSimulate);
            }
            return requestTimeout408(responseSimulate);
        }

        if (httpCode == 409) {
            if (throwable) {
                throw conflict409(responseSimulate);
            }
            return conflict409(responseSimulate);
        }

        if (httpCode == 410) {
            if (throwable) {
                throw gone410(responseSimulate);
            }
            return gone410(responseSimulate);
        }

        if (httpCode == 411) {
            if (throwable) {
                throw lengthRequired411(responseSimulate);
            }
            return lengthRequired411(responseSimulate);
        }

        if (httpCode == 412) {
            if (throwable) {
                throw preconditionFailed412(responseSimulate);
            }
            return preconditionFailed412(responseSimulate);
        }

        if (httpCode == 413) {
            if (throwable) {
                throw payloadTooLarge413(responseSimulate);
            }
            return payloadTooLarge413(responseSimulate);
        }

        if (httpCode == 414) {
            if (throwable) {
                throw uriTooLong414(responseSimulate);
            }
            return uriTooLong414(responseSimulate);
        }

        if (httpCode == 415) {
            if (throwable) {
                throw unsupportedMediaType415(responseSimulate);
            }
            return unsupportedMediaType415(responseSimulate);
        }

        if (httpCode == 416) {
            if (throwable) {
                throw rangeNotSatisfiable416(responseSimulate);
            }
            return rangeNotSatisfiable416(responseSimulate);
        }

        if (httpCode == 417) {
            if (throwable) {
                throw expectationFailed417(responseSimulate);
            }
            return expectationFailed417(responseSimulate);
        }

        if (httpCode == 418) {
            if (throwable) {
                throw iamTeapot418(responseSimulate);
            }
            return iamTeapot418(responseSimulate);
        }

        if (httpCode == 421) {
            if (throwable) {
                throw misDirectedRequest421(responseSimulate);
            }
            return misDirectedRequest421(responseSimulate);
        }

        if (httpCode == 422) {
            if (throwable) {
                throw unprocessableContent422(responseSimulate);
            }
            return unprocessableContent422(responseSimulate);
        }

        if (httpCode == 423) {
            if (throwable) {
                throw locked423(responseSimulate);
            }
            return locked423(responseSimulate);
        }

        if (httpCode == 424) {
            if (throwable) {
                throw failedDependence424(responseSimulate);
            }
            return failedDependence424(responseSimulate);
        }

        if (httpCode == 425) {
            if (throwable) {
                throw tooEarly425(responseSimulate);
            }
            return tooEarly425(responseSimulate);
        }

        if (httpCode == 426) {
            if (throwable) {
                throw upgradeRequired426(responseSimulate);
            }
            return upgradeRequired426(responseSimulate);
        }

        if (httpCode == 428) {
            if (throwable) {
                throw preconditionRequired428(responseSimulate);
            }
            return preconditionRequired428(responseSimulate);
        }

        if (httpCode == 429) {
            if (throwable) {
                throw tooManyRequests429(responseSimulate);
            }
            return tooManyRequests429(responseSimulate);
        }

        if (httpCode == 431) {
            if (throwable) {
                throw requestHeaderFieldsTooLarge431(responseSimulate);
            }
            return requestHeaderFieldsTooLarge431(responseSimulate);
        }

        if (httpCode == 451) {
            if (throwable) {
                throw unavailableForLegalReasons451(responseSimulate);
            }
            return unavailableForLegalReasons451(responseSimulate);
        }

        if (httpCode == 500) {
            if (throwable) {
                throw internalServerError500(responseSimulate);
            }
            return internalServerError500(responseSimulate);
        }

        if (httpCode == 501) {
            if (throwable) {
                throw notImplemented501(responseSimulate);
            }
            return notImplemented501(responseSimulate);
        }

        if (httpCode == 502) {
            if (throwable) {
                throw badGateway502(responseSimulate);
            }
            return badGateway502(responseSimulate);
        }

        if (httpCode == 503) {
            if (throwable) {
                throw serviceUnavailable503(responseSimulate);
            }
            return serviceUnavailable503(responseSimulate);
        }

        if (httpCode == 504) {
            if (throwable) {
                throw gatewayTimeout504(responseSimulate);
            }
            return gatewayTimeout504(responseSimulate);
        }

        if (httpCode == 505) {
            if (throwable) {
                throw httpVersionNotSupported505(responseSimulate);
            }
            return httpVersionNotSupported505(responseSimulate);
        }

        if (httpCode == 506) {
            if (throwable) {
                throw variantAlsoNegotiates506(responseSimulate);
            }
            return variantAlsoNegotiates506(responseSimulate);
        }

        if (httpCode == 507) {
            if (throwable) {
                throw insufficientStorage507(responseSimulate);
            }
            return insufficientStorage507(responseSimulate);
        }

        if (httpCode == 508) {
            if (throwable) {
                throw loopDetected508(responseSimulate);
            }
            return loopDetected508(responseSimulate);
        }

        if (httpCode == 510) {
            if (throwable) {
                throw notExtended510(responseSimulate);
            }
            return notExtended510(responseSimulate);
        }

        if (httpCode == 511) {
            if (throwable) {
                throw networkAuthenticationRequired511(responseSimulate);
            }
            return networkAuthenticationRequired511(responseSimulate);
        }

        throw new RuntimeException("[restResponseSimulate] Wrong/Unknown HTTP Code");
    }

    public static String httpResponseErrorExtractor(HttpStatusCodeException hc) {

        String bodyString = hc.getResponseBodyAsString();
        String statusText = hc.getStatusText();
        String statusCode = String.valueOf(hc.getStatusCode());
        String headers = String.valueOf(hc.getResponseHeaders());

        return "Body{"+bodyString+"} StatusText{"+statusText+"} StatusCode{"+statusCode+"} Headers{"+headers+"}";

    }

}
