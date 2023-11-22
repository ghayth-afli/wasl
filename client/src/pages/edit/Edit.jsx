import React, { useState, useEffect} from "react";
import "./Edit.scss";
import {
  Button,
  TextField,
  Select,
  MenuItem,
  InputLabel,
  FormControl,
} from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { styled } from "@mui/material/styles";
import { hosts } from "../../const";
import { Link, useNavigate, useParams } from "react-router-dom";
import Alert from "@mui/material/Alert";
import AlertTitle from "@mui/material/AlertTitle";

const Update = () => {
  const navigate = useNavigate();
  const {id} = useParams();
  const [error, setError] = useState(null);
  const VisuallyHiddenInput = styled("input")({
    clip: "rect(0 0 0 0)",
    clipPath: "inset(50%)",
    height: 1,
    overflow: "hidden",
    position: "absolute",
    bottom: 0,
    left: 0,
    whiteSpace: "nowrap",
    width: 1,
  });

  const [formValues,setFormValues] = useState({
    title: "",
    category: "",
    description: "",
    depart: "",
    destination: "",
    date: "",
    time: "",
    deliveryTime: "",
    capacity: "",
    price: "",
    image: "",
  });
  const{title,category,description,depart,destination,date,time,deliveryTime,capacity,price,image}=formValues;
  
  const [file, setFile] = useState(null);
    
  useEffect(() => {
    
    loadOfferDetails();
  }, [id]);
 
  const loadOfferDetails = async () => {
    try {
       const response = await fetch(`${hosts.backend}/api/offers/${id}`,formValues, {
        headers: {
              Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });

      if (!response.ok) {
        setError(true);
        throw new Error("HTTP error " + response.status);
      }

      const data = await response.json();
      setFile(data);
      setFormValues(data);
      
    } catch (error) {
      console.error("Fetch error:", error);
      setError(true);
    }
  };

  const handleFileChange = (event) => {
    setFormValues(event.target.files[0]);
  };

  const handleChange = (event) => {
    setFormValues({
      ...formValues,
      [event.target.name]: event.target.value,
    });
  };

  const handleUpdate = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch(`${hosts.backend}/api/offers/${id}`,formValues,{
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify(formValues),
      });

      if (!response.ok) {
        setError(true);
        throw new Error("HTTP error " + response.status);
      }

      setError(false);
      navigate("/mygigs");
    } catch (error) {
      console.log("Fetch error:", error);
      setError(true);
    }
  };

  return (
    <>
      {error === true && (
        <Alert severity="error">
          <AlertTitle>Error</AlertTitle>
          Offer not updating — <strong>check it out again!</strong>
        </Alert>
      )}
      {error === false && (
        <Alert severity="success">
          <AlertTitle>Success</AlertTitle>
          Offer created —{" "}
          <strong>
            {" "}
            <Link to="/mygigs">check it out!</Link>{" "}
          </strong>
        </Alert>
      )}
      <form onSubmit={handleUpdate}>
        <div className="edit">
          <div className="container">
            <h1>Update Gig</h1>
            <TextField
                  name="title"
                  label="Title"
                  placeholder="e.g. I will do something I'm really good at"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <FormControl fullWidth>
                  <InputLabel>Category</InputLabel>
                  <Select name="category" required onChange={handleChange}>
                    <MenuItem value="clothes">Clothes</MenuItem>
                    <MenuItem value="electronics">Electronics </MenuItem>
                    <MenuItem value="food">Food</MenuItem>
                    <MenuItem value="meds">Meds</MenuItem>
                  </Select>
                </FormControl>
                <InputLabel>Offer Images</InputLabel>
                <Button
                  component="label"
                  variant="outlined"
                  startIcon={<CloudUploadIcon />}
                >
                  Upload file
                  <input type="file" onChange={handleFileChange} />
                </Button>
                <TextField
                  name="description"
                  label="Description"
                  placeholder="Brief descriptions to introduce your service to customers"
                  multiline
                  rows={16}
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <Button variant="contained" type="submit">
                  Update
                </Button>
              </div>
              <div className="details">
                <TextField
                  name="depart"
                  label="Depart"
                  placeholder="e.g. Tunisie Carthage"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="destination"
                  label="Destination"
                  placeholder="e.g. Germany Berlin"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="date"
                  label="Date"
                  type="date"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="time"
                  label="Time"
                  type="time"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="deliveryTime"
                  label="Delivery Time (e.g. 3 days)"
                  type="number"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="capacity"
                  label="Capacity"
                  placeholder="e.g. 5 Kg"
                  fullWidth
                  required
                  onChange={handleChange}
                />
                <TextField
                  name="price"
                  label="Price"
                  type="number"
                  fullWidth
                  required
                  onChange={handleChange}
                />
              </div>
          </div>
      </form>
    </>
  );
};

export default Update;