//
//  ViewController.h
//  VergeRssFeed
//
//  Created by SigmaTek on 3.02.2016.
//  Copyright © 2016 Furkan Avcu. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RssFeed.h"
@import SafariServices;

@interface ViewController : UIViewController <NSXMLParserDelegate,UITableViewDataSource,UITableViewDelegate,SFSafariViewControllerDelegate>
{
    NSXMLParser *parser;
    NSMutableArray *entryList;
    RssFeed *feed;
    NSString *currentEntry;
    NSData *responseData;
}
@property (weak, nonatomic) IBOutlet UITableView *tableView;

@end

