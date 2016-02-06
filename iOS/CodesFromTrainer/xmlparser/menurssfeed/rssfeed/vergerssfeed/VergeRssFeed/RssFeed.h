//
//  RssFeed.h
//  VergeRssFeed
//
//  Created by SigmaTek on 3.02.2016.
//  Copyright © 2016 Furkan Avcu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface RssFeed : NSObject
@property (nonatomic,strong) NSMutableString *title;
@property (nonatomic,strong) NSMutableString *content;
@property (nonatomic,strong) NSMutableString *link;
@property (nonatomic,strong) NSMutableString *imgName;
@property (nonatomic,strong) NSMutableString *author;
@end
