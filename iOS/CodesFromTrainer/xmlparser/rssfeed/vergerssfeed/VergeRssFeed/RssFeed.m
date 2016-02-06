//
//  RssFeed.m
//  VergeRssFeed
//
//  Created by SigmaTek on 3.02.2016.
//  Copyright © 2016 Furkan Avcu. All rights reserved.
//

#import "RssFeed.h"

@implementation RssFeed
@synthesize title,content,imgName,link,author;

- (instancetype)init
{
    self = [super init];
    if (self) {
        //XMLParse elementleri bir anda okuyamıyor internet hızı vs. sebeplerden dolayı o yüzden NSMutableString
        title=[[NSMutableString alloc] init];
        content=[[NSMutableString alloc] init];
        imgName=[[NSMutableString alloc] init];
        link =[[NSMutableString alloc] init];
        author =[[NSMutableString alloc] init];
    
    }
    return self;
}
@end
