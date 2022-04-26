import {useMutation, useQuery, useQueryClient} from "react-query";
import {
    AddListItemRequestBody,
    AddListRequestBody,
    callAddList, callAddListItem,
    callGetList,
    callGetListItems,
    callGetLists,
    ShoppingList
} from "./listRestCalls";
import {AxiosError} from "axios";


export const useGetListsQuery = () => useQuery("list-query", () => callGetLists())
export const useGetListQuery = (id: string) => useQuery(`list-detail-query-${id}`, () => callGetList(id))
export const useGetListItemsQuery = (id: string) => useQuery(`list-detail-query-items-${id}`, () => callGetListItems(id))

export const useAddListMutation = () => {
    const queryClient = useQueryClient();
    const createList = (content: AddListRequestBody) => {
        return callAddList(content)
    }
    return useMutation<ShoppingList, AxiosError, AddListRequestBody>(createList, {
        onSuccess: () => queryClient.invalidateQueries("list-query")
    });
}

export const useAddListItemMutation = (listId: string) => {
    const queryClient = useQueryClient();
    const createListItem = (content: AddListItemRequestBody) => {
        return callAddListItem(listId, content)
    }
    return useMutation<ShoppingList, AxiosError, AddListItemRequestBody>(createListItem, {
        onSuccess: () => queryClient.invalidateQueries(`list-detail-query-items-${listId}`)
    });
};
