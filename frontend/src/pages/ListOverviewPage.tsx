import {useAddListMutation, useGetListsQuery} from "../queries/listQueries";
import {Alert, Box, Button, Card, Container, Paper, TextField, Typography} from "@mui/material";
import {Link} from "react-router-dom";
import {useState} from "react";

export const ListOverviewPage = () => {
    return (
        <Container>
            <Typography variant="h2">Einkaufslisten</Typography>
            <Box sx={{mb: 2}}>
                <ListOverviewList/>
            </Box>

            <AddListCard/>
        </Container>
    )
}

export const ListOverviewList = () => {

    const {data = [], isLoading, isError} = useGetListsQuery();

    if (isLoading) {
        return (
            <Typography>
                Liste wird geladen
            </Typography>
        )
    }
    if (isError) {
        return (
            <Typography>
                Liste konnte nicht geladen werden
            </Typography>
        )
    }

    if (data.length === 0) {
        return (
            <Typography>
                Liste ist leer
            </Typography>
        )
    }
    return (
        <div>
            {
                data.map(it => (
                    <Card key={it.id} sx={{mb: 2, p: 2}}>
                        <Typography variant={"h3"}>
                            {it.name}
                        </Typography>

                        <Typography>
                            <Link to={"/details/" + it.id}>Details anzeigen</Link>
                        </Typography>
                    </Card>
                ))
            }
        </div>
    );
}

const AddListCard = () => {
    const [name, setName] = useState<string>("");

    const mutation = useAddListMutation();
    const onClick = () => {
        mutation.mutateAsync({name}).then(list => {
            console.log("list: ", list)
        })
    }
    return (
        <Card sx={{p: 2}}>
            <Typography variant={"h3"}>Neue Liste anlegen</Typography>
            {
                mutation.error && (
                    <Alert severity="error">{mutation.error.message}</Alert>
                )
            }

            <Box sx={{display: "flex", justifyContent:"flexStart"}}>
                <TextField placeholder={"Name"} value={name} onChange={(e) => setName(e.target.value)}/>
                <Button sx={{ml:2}} onClick={onClick} variant={"contained"}>Anlegen</Button>
            </Box>
        </Card>
    )
}