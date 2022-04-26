import {
    useAddListItemMutation,
    useAddListMutation,
    useGetListItemsQuery,
    useGetListQuery
} from "../queries/listQueries";
import {useNavigate, useParams} from "react-router-dom";
import {Alert, Box, Button, Card, Container, TextField, Typography} from "@mui/material";
import {useState} from "react";

export const ListDetailPage = () => {
    return (
        <Container>
            <ListDetails/>
        </Container>
    )
}


export const ListDetails = () => {
    const {id} = useParams<Record<"id", string>>()
    console.log("id: ", id)
    const {data: list, isLoading: listIsLoading, isError} = useGetListQuery(id!!)

    if (listIsLoading) {
        return (<Box>loading</Box>);
    }

    if (isError) {
        return (<Box>error loading list</Box>);
    }
    if (!list) {
        return (<Box>could not find list</Box>);
    }

    return (
        <Box>
            <Typography variant={"h2"}>{list.name}</Typography>
            <ItemList id={id!!}/>
            <AddListItem id={id!!}/>
        </Box>
    )
}

const ItemList = (props: { id: string }) => {
    const {data = [], isLoading, isError} = useGetListItemsQuery(props.id);
    if (isLoading) {
        return (<Box>loading</Box>);
    }

    if (isError) {
        return (<Box>error loading list</Box>);
    }
    if (data.length === 0) {
        return (<Box>Keine Waren in der Einkaufsliste vorhanden</Box>);
    }

    return (
        <Box>
            {
                data.map(item => (
                    <Box mb={2} key={item.id}>
                        <Card sx={{p: 2}}>
                            <Typography variant={"h3"}>{item.name}</Typography>
                            <Typography>{item.amount} Stück</Typography>
                        </Card>
                    </Box>
                ))
            }
        </Box>
    )
}

const AddListItem = (props: { id: string }) => {
    const [name, setName] = useState<string>("");
    const [amount, setAmount] = useState<number>(0);

    const mutation = useAddListItemMutation(props.id);
    const onClick = () => {
        mutation.mutateAsync({name, amount}).then(list => {
            console.log("list: ", list)
        })
    }
    return (
        <Card sx={{p: 2}}>
            <Typography variant={"h3"}>Ware hinzufügen</Typography>
            {
                mutation.error && (
                    <Alert severity="error">{mutation.error.message}</Alert>
                )
            }

            <Box sx={{display: "flex", justifyContent: "flexStart"}}>
                <TextField placeholder={"Name"} value={name} onChange={(e) => setName(e.target.value)}/>
                <TextField sx={{ml:2}}  placeholder={"Anzahl"} value={amount} type={"number"}
                           onChange={(e: any) => setAmount(e.target.valueAsNumber)}/>
                <Button sx={{ml:2}} onClick={onClick} variant={"contained"}>Anlegen</Button>
            </Box>
        </Card>
    )
}