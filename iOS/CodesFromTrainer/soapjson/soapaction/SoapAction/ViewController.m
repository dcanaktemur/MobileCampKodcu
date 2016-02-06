//
//  ViewController.m
//  SoapAction
//
//  Created by SigmaTek on 3.02.2016.
//  Copyright © 2016 Furkan Avcu. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()
@property NSString *soapMessage;
@property NSString *currentElement;
@property NSMutableData *webResponseData;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)convert:(id)sender {
    NSString *soapMessage;
    soapMessage = [NSString stringWithFormat:@"<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                   "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                   "<soap:Body>"
                   "<CelsiusToFahrenheit xmlns=\"http://www.w3schools.com/webservices/\">"
                   "<Celsius>%@"
                   "</Celsius>"
                   "</CelsiusToFahrenheit>"
                   "</soap:Body>"
                   "</soap:Envelope>",self.celcius.text];
    
    NSURL *url = [NSURL URLWithString:@"http://www.w3schools.com/webservices/tempconvert.asmx?op=CelsiusToFahrenheit"];
    NSMutableURLRequest *theRequest = [NSMutableURLRequest requestWithURL:url];
    
    [theRequest addValue:@"www.w3schools.com" forHTTPHeaderField:@"Host"];
    [theRequest addValue: @"text/xml; charset=utf-8" forHTTPHeaderField:@"Content-Type"];
    [theRequest addValue: @"http://www.w3schools.com/webservices/CelsiusToFahrenheit" forHTTPHeaderField:@"SOAPAction"];
    
    [theRequest setHTTPMethod:@"POST"];
    NSMutableData *body = [NSMutableData data];
    [body appendData:[soapMessage dataUsingEncoding:NSUTF8StringEncoding]];
    [theRequest setHTTPBody: body];
    
    NSString *postLength = [NSString stringWithFormat:@"%lu", (unsigned long)[body length]];
    [theRequest setValue:postLength forHTTPHeaderField:@"Content-Length"];
    
    
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration delegate:self delegateQueue:nil];
    NSURLSessionDataTask *postDataTask = [session dataTaskWithRequest:theRequest completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
        NSString *theXML = [[NSString alloc] initWithBytes:
                            [data bytes] length:[data length] encoding:NSUTF8StringEncoding];
        
        NSLog(@"%@",theXML);
        NSData *myData = [theXML dataUsingEncoding:NSUTF8StringEncoding];
        NSXMLParser *xmlParser = [[NSXMLParser alloc] initWithData:myData];
        xmlParser.delegate = self;
        
        @try{
            [xmlParser parse];
        }
        @catch (NSException* exception)
        {
            
            UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"Server Error" message:[exception reason] preferredStyle:UIAlertControllerStyleAlert];
            
            UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action)
                                       {
                                           [alert dismissViewControllerAnimated:YES completion:nil];
                                       }];
            [alert addAction:okAction];
            [self presentViewController:alert animated:YES completion:nil];
            return;
        }
    }];
    
    [postDataTask resume];
}

//NSXmlParserDelegate method ları
-(void) parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName
  namespaceURI:(NSString *)namespaceURI qualifiedName:
(NSString *)qName attributes:(NSDictionary *)attributeDict
{
    _currentElement = elementName;
}
- (void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
    if ([_currentElement isEqualToString:@"CelsiusToFahrenheitResult"]) {
        
        dispatch_async(dispatch_get_main_queue(), ^(){
            self.fahrenheitTextView.text=string;
            UIAlertController *alert = [UIAlertController alertControllerWithTitle:@"Result" message:
                                        [NSString stringWithFormat:@"%@ C = %@ F",self.celcius.text,string] preferredStyle:UIAlertControllerStyleAlert];
            
            UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action)
                                       {
                                           [alert dismissViewControllerAnimated:YES completion:nil];
                                       }];
            [alert addAction:okAction];
            [self presentViewController:alert animated:YES completion:nil];

        });
        
    }
    if ([_currentElement isEqualToString:@"EkgUploadFileResult"]) {
        NSLog(@"EKGResult:%@",string);
    }
}

- (void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName
  namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
    //  NSLog(@"Parsed Element : %@", currentElement);
}

//------------------------------------------------------------------------------
- (void)postXML {
    NSString *filePath = [[NSBundle mainBundle] pathForResource:@"ecg" ofType:@"pdf"];
    NSData *myPDFData = [NSData dataWithContentsOfFile:filePath];
    NSString *base64=[[NSString alloc]init];
    
    base64=[myPDFData  base64EncodedStringWithOptions:NSDataBase64Encoding64CharacterLineLength];
    NSString *soapMessage;
    soapMessage = [NSString stringWithFormat:@"<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                   "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                   "<soap:Body>"
                   "<EkgUploadFile xmlns=\"http://tempuri.org/\">"
                   "<f>%@"
                   "</f>"
                   "<fileName>%@</fileName>"
                   "</EkgUploadFile>"
                   "</soap:Body>"
                   "</soap:Envelope>",base64, @"ecg.pdf"];
    
    NSURL *url;
    url = [NSURL URLWithString:@"http://212.174.119.114/WebService.asmx?op=EkgUploadFile"];
    
    NSMutableURLRequest *theRequest = [NSMutableURLRequest requestWithURL:url];
    
    [theRequest addValue:@"212.174.119.114" forHTTPHeaderField:@"Host"];
    [theRequest addValue: @"text/xml; charset=utf-8" forHTTPHeaderField:@"Content-Type"];
    [theRequest addValue: @"http://tempuri.org/EkgUploadFile" forHTTPHeaderField:@"SOAPAction"];
    
    
    [theRequest setHTTPMethod:@"POST"];
    NSMutableData *body = [NSMutableData data];
    [body appendData:[soapMessage dataUsingEncoding:NSUTF8StringEncoding]];
    [theRequest setHTTPBody: body];
    
    NSString *postLength = [NSString stringWithFormat:@"%lu", (unsigned long)[body length]];
    [theRequest setValue:postLength forHTTPHeaderField:@"Content-Length"];
    
    //initiate the request
    NSURLConnection *connection =
    [[NSURLConnection alloc] initWithRequest:theRequest delegate:self];
    
    if(connection)
    {
        _webResponseData = [NSMutableData data] ;
    }
    else
    {
        NSLog(@"Connection is NULL");
    }
    
}

//Bağlantı delege methodlarının implementasyonu
-(void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response {
    [self.webResponseData  setLength:0];
}
// web servise http post edildiğinde gelen cevabın alındığı method
-(void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data {
    [self.webResponseData  appendData:data];
}
// Web servis ile bağlantının bir hata ile sonuçlandığında çağrılan method
-(void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error {
    NSLog(@"Some error in your Connection. Please try again.");
}
// http post işlemi tamamlanırken gelen web response data' nın işlendiği method
-(void)connectionDidFinishLoading:(NSURLConnection *)connection {
    NSLog(@"Received %lu Bytes", (unsigned long)[_webResponseData length]);
    NSString *theXML = [[NSString alloc] initWithBytes:
                        [_webResponseData mutableBytes] length:[_webResponseData length] encoding:NSUTF8StringEncoding];
    
    NSLog(@"%@",theXML);
    
    NSData *myData = [theXML dataUsingEncoding:NSUTF8StringEncoding];
    
    NSXMLParser *xmlParser = [[NSXMLParser alloc] initWithData:myData];
    
    xmlParser.delegate = self;
    
    @try{
        [xmlParser parse];
    }
    @catch (NSException* exception)
    {
        UIAlertView* alert = [[UIAlertView alloc]initWithTitle:@"Server Error" message:[exception reason] delegate:self cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alert show];
        return;
    }
}
//------------------------------------------------------------------------------


//GETJSON

- (IBAction)getJSON:(id)sender {
    
    NSMutableURLRequest *request=[[NSMutableURLRequest alloc]init];
    [request setURL:[NSURL URLWithString:@"https://httpbin.org/get"]];
    [request setHTTPMethod:@"GET"];
    [request addValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    [request addValue:@"httpbin.org" forHTTPHeaderField:@"Host"];

    //    NSString *authHeader = [NSString stringWithFormat:@"%@",deviceId];
    //    [request addValue:authHeader forHTTPHeaderField:@"token"] ;
    [request addValue:@"no-cache" forHTTPHeaderField:@"Cache-Control"];
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration delegate:self delegateQueue:nil];

    NSURLSessionDataTask *postDataTask = [session dataTaskWithRequest:request completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
        NSError *jsonError;
        NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data
                                                             options:NSJSONReadingMutableContainers
                              
                                                               error:&jsonError];
        NSLog(@"GETJSON: %@",json);
    }];
    
    [postDataTask resume];

}

- (IBAction)postJSON:(id)sender {
    
    NSDateFormatter *format = [[NSDateFormatter alloc] init];
    [format setDateFormat:@" yyyyMMddHHmm"];
    NSDate *now = [[NSDate alloc] init];
    NSString *currentdate = [format stringFromDate:now];
    
    NSString *queryString = [NSString stringWithFormat:@"https://httpbin.org/post"];
    NSMutableURLRequest *theRequest=[NSMutableURLRequest
                                     requestWithURL:[NSURL URLWithString:
                                                     queryString]
                                     cachePolicy:NSURLRequestUseProtocolCachePolicy
                                     timeoutInterval:60.0];
    
    NSDictionary* jsonDictionary = [NSDictionary dictionaryWithObjectsAndKeys:
                                    @"ios",@"platform",
                                    currentdate,@"createDate",
                                    nil];
    
    NSError *error;
    NSData* jsonData = [NSJSONSerialization dataWithJSONObject:jsonDictionary
                                                       options:NSJSONWritingPrettyPrinted error:&error];
    [theRequest setHTTPMethod:@"POST"];
    [theRequest addValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    [theRequest addValue:@"no-cache" forHTTPHeaderField:@"Cache-Control"];
    
    // should check for and handle errors here but we aren't
    [theRequest setHTTPBody:jsonData];
    NSString *postLength = [NSString stringWithFormat:@"%lu", (unsigned long)[jsonData length]];
    [theRequest setValue:postLength forHTTPHeaderField:@"Content-Length"];
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration delegate:self delegateQueue:nil];
    
    NSURLSessionDataTask *postDataTask = [session dataTaskWithRequest:theRequest completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
        NSError *jsonError;
        NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data
                                                             options:NSJSONReadingMutableContainers
                              
                                                               error:&jsonError];
        NSLog(@"JSONResponse: %@",json);
    }];
    
    [postDataTask resume];
    
}


@end
