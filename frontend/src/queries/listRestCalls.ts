import axios from "axios";

export interface ShoppingList {
    readonly id: string;
    readonly name: string;
}

export interface ShoppingListItem {
    readonly id: string;
    readonly name: string;
    readonly amount: number;
}

export interface AddListRequestBody {
    readonly name: string;
}
export interface AddListItemRequestBody {
    readonly name: string;
    readonly amount: number;
}

export const callGetLists = () => axios.get<ShoppingList[]>("/api/list").then(r => Promise.resolve(r.data));
export const callGetList = (id: string) => axios.get<ShoppingList>("/api/list/" + id).then(r => Promise.resolve(r.data));
export const callAddList = (body: AddListRequestBody) => axios.post<ShoppingList>("/api/list", body).then(r => Promise.resolve(r.data)).catch(err => Promise.reject(err.response.data));
export const callGetListItems = (listId: string) => axios.get<ShoppingListItem[]>(`/api/list/${listId}/items`).then(r => Promise.resolve(r.data));
export const callAddListItem = (listId: string, body: AddListItemRequestBody) => axios.post<ShoppingListItem>(`/api/list/${listId}/items`, body).then(r => Promise.resolve(r.data)).catch(err => Promise.reject(err.response.data));
