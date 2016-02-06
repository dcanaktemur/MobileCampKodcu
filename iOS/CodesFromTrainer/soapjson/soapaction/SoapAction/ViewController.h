//
//  ViewController.h
//  SoapAction
//
//  Created by SigmaTek on 3.02.2016.
//  Copyright © 2016 Furkan Avcu. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController <NSXMLParserDelegate,NSURLSessionDelegate,NSURLConnectionDelegate>

@property (weak, nonatomic) IBOutlet UITextField *celcius;
- (IBAction)convert:(id)sender;
@property (weak, nonatomic) IBOutlet UITextView *fahrenheitTextView;

- (IBAction)getJSON:(id)sender;
- (IBAction)postJSON:(id)sender;
@end

