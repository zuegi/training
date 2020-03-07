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

  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(value => {
      this.listProducts();
    });
  }

  listProducts() {
    // checks for defined id
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      // get the "id" string param. convert string to a number using the + symbold
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
    } else {
      // no category id --> defaults to 1
      this.currentCategoryId = 1;
    }

    // get the products by category id
    this.productService.getProductList(this.currentCategoryId).subscribe(
      value => {
        this.products = value;
      });
  }
}
