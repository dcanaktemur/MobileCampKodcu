//
//  ViewController.m
//  VergeRssFeed
//
//  Created by SigmaTek on 3.02.2016.
//  Copyright © 2016 Furkan Avcu. All rights reserved.
//

#import "ViewController.h"
@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self getRssData];
    self.tableView.dataSource=self;
    self.tableView.delegate=self;
    self.title=@"The Verge RSS Reader";
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
#pragma mark TableView datasource 
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [entryList count];
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *celliD=@"feedCell";
    RssFeed *temp=[entryList objectAtIndex:indexPath.row];
    
    UITableViewCell *cell=[self.tableView dequeueReusableCellWithIdentifier:celliD];
    cell.textLabel.text=temp.title;
    cell.detailTextLabel.text=temp.author;
    NSURL *imageURL = [NSURL URLWithString:temp.imgName];
    cell.tag = indexPath.row;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
        NSData *imageData = [NSData dataWithContentsOfURL:imageURL];
        
        dispatch_async(dispatch_get_main_queue(), ^{
            // Update the UI
             if (cell.tag == indexPath.row) {
            cell.imageView.image = [UIImage imageWithData:imageData];
             [cell setNeedsLayout];
             }
        });
    });
    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    RssFeed *temp=[entryList objectAtIndex:indexPath.row];

    [self displaySafari:[NSURL URLWithString:temp.link]];
}
- (void)getRssData{
    NSURL *url = [NSURL URLWithString:@"http://www.theverge.com/rss/frontpage"];
    //NSURLConnection is deprecated iOS 9 NSURLSession ile NSURLConnection farkına yardımcı olacak ref:https://www.objc.io/issues/5-ios7/from-nsurlconnection-to-nsurlsession/
    
    NSURLSession *session = [NSURLSession sharedSession];
    [[session dataTaskWithURL:url completionHandler:^(NSData *data,NSURLResponse *response,NSError *error) {
                responseData=data;
//                NSLog(@"%@",[[NSString alloc] initWithData:responseData encoding:NSUTF8StringEncoding]);
                parser = [[NSXMLParser alloc] initWithData:responseData];
                parser.delegate=self;
                entryList = [[NSMutableArray alloc] init];
                [parser parse];
                
            }] resume];

}
- (void)displaySafari: (NSURL*) url{
    SFSafariViewController *safariVC = [[SFSafariViewController alloc]initWithURL:url entersReaderIfAvailable:NO];
    safariVC.delegate = self;
    [self presentViewController:safariVC animated:NO completion:nil];
}
-(void)parser:(NSXMLParser *)parser didStartElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName attributes:(NSDictionary<NSString *,NSString *> *)attributeDict
{
    currentEntry = elementName;
    if ([currentEntry isEqualToString:@"entry"]) {
        feed =[[RssFeed alloc] init];
    }
    if ([currentEntry isEqual:@"link"])
    {
        //<link rel="alternate" type="text/html" href="http://www.theverge.com/2016/2/3/10905672/google-samsung-adblock-fast-android-ad-blocker-removal"/>
        NSString *link = [attributeDict objectForKey:@"href"];
        [feed.link appendString:link];
    }
    
}
-(void)parser:(NSXMLParser *)parser foundCharacters:(NSString *)string
{
    if ([currentEntry isEqualToString:@"title"])
        [feed.title appendString:string];
    if ([currentEntry isEqualToString:@"content"]){
        [feed.content appendString:string];
        
    }
    if ([currentEntry isEqualToString:@"name"])
        [feed.author appendString:string];

}

-(void)parser:(NSXMLParser *)parser didEndElement:(NSString *)elementName namespaceURI:(NSString *)namespaceURI qualifiedName:(NSString *)qName
{
    if ([elementName isEqualToString:@"entry"]) {
        //imageURL content içinde yer almaktadır html parser ile uğraşmamak için custom method kullanılmıştır.
        NSString *imgUrlString = [self substringfromto:feed.content :@"src=\"" :@"\" />"];
        [feed.imgName appendString: imgUrlString];
        [entryList addObject:feed];
        
    }
}
-(void)parserDidEndDocument:(NSXMLParser *)parser
{
    //User Interface ile ilgili methodlar main thread' de olmalıdır.
    dispatch_async(dispatch_get_main_queue(), ^{
        [self.tableView reloadData];
    });
}
-(NSString *)substringfromto:(NSString *)all_string :(NSString*)from_string :(NSString*)to_string{
    NSString *param = nil;
    NSRange start = [all_string rangeOfString:from_string];
    if (start.location != NSNotFound)
    {
        param = [all_string substringFromIndex:start.location + start.length];
        NSRange end = [param rangeOfString:to_string];
        if (end.location != NSNotFound)
        {
            param = [param substringToIndex:end.location];
        }
    }
    return param;
}

@end
