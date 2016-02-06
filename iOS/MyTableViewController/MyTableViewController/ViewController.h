//
//  ViewController.h
//  MyTableViewController
//
//  Created by Gokce123 on 06/02/16.
//  Copyright © 2016 Gokce123. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController<UITableViewDataSource,UITableViewDelegate>

@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

