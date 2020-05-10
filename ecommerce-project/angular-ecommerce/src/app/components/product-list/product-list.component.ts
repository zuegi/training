import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';
import {Product} from '../../common/product';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  products: Product[];
  currentCategoryId: number;
  currentCategoryName: string;
  searchMode: boolean;

  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(value => {
      this.listProducts();
    });
  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if (this.searchMode) {
      this.handleSearchProducts();
    } else {
      this.handleListProducts();
    }
  }

  private handleSearchProducts() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword');
    // now search for the products using keyword
    this.productService.searchProducts(theKeyword).subscribe(
      data => {
        this.products = data;
      }
    );
  }

  handleListProducts() {
    // checks for defined id
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      // get the "id" string param. convert string to a number using the + symbold
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
      // get the "name" param string
      this.currentCategoryName = this.route.snapshot.paramMap.get('name');
    } else {
      // no category id --> defaults to 1
      this.currentCategoryId = 1;
      this.currentCategoryName = 'Books';
    }

    // get the products by category id
    this.productService.getProductList(this.currentCategoryId).subscribe(
      value => {
        this.products = value;
      });
  }

}