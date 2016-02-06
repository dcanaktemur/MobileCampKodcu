//
//  ViewController.m
//  MyTableViewController
//
//  Created by Gokce123 on 06/02/16.
//  Copyright © 2016 Gokce123. All rights reserved.
//

#import "ViewController.h"
#import "MyViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    _tableView.delegate = self;
    _tableView.dataSource = self;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 5;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *cellID = @"mycell";
    UITableViewCell *cell = [_tableView dequeueReusableCellWithIdentifier:cellID forIndexPath:indexPath];
    
    cell.textLabel.text = @"campKodcu";
    
    return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
     UITableViewCell *cell = [_tableView cellForRowAtIndexPath:indexPath];
    MyViewController *myVC = [self.storyboard instantiateViewControllerWithIdentifier:@"myVC"];
    [myVC setMyproperty:cell.textLabel.text];
    myVC.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;
    [self.navigationController pushViewController:myVC animated:YES];
}

@end
