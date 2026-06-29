import { Routes } from '@angular/router';

import { Accounts }
from './components/accounts/accounts';

import { AccountDetail }
from './components/account-detail/account-detail';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'accounts',
    pathMatch: 'full'
  },

  {
    path: 'accounts',
    component: Accounts
  },

  {
    path: 'accounts/:accountNumber',
    component: AccountDetail
  }
];