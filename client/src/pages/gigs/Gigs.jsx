import React, { useEffect, useRef, useState } from "react";
import "./Gigs.scss";
import { gigs } from "../../data";
import GigCard from "../../components/gigCard/GigCard";
import { hosts } from "../../const.js";
import { ChakraProvider } from "@chakra-ui/react";

function Gigs() {
  window.scrollTo(0, 0);
  const [sort, setSort] = useState("sales");
  const [open, setOpen] = useState(false);
  const [gigs, setGigs] = useState([]);
  const minRef = useRef();
  const maxRef = useRef();

  useEffect(() => {
    fetch(`${hosts.backend}/api/offers`, {
      method: "GET",
      headers: {
        "Content-Type": "Application/json ; charset=UTF-8",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setGigs(data);
      });
  }, []);

  const reSort = (type) => {
    setSort(type);
    setOpen(false);
  };

  const apply = () => {
    console.log(minRef.current.value);
    console.log(maxRef.current.value);
  };

  return (
    <div className="gigs">
      <div className="container">
        <span className="breadcrumbs">Transportation Offers</span>
        <h1>Tranportation offers</h1>
        <p>
          Explore the best transportation offers on Liverr. Find services to
          help you get around safely and comfortably.{" "}
        </p>
        <div className="menu">
          <div className="left">
            <span>Budget</span>
            <input ref={minRef} type="number" placeholder="min" />
            <input ref={maxRef} type="number" placeholder="max" />
            <button onClick={apply}>Apply</button>
          </div>
          <div className="right">
            <span className="sortBy">Sort by</span>
            <span className="sortType">
              {sort === "sales" ? "Best Selling" : "Newest"}
            </span>
            <img src="./img/down.png" alt="" onClick={() => setOpen(!open)} />
            {open && (
              <div className="rightMenu">
                {sort === "sales" ? (
                  <span onClick={() => reSort("createdAt")}>Newest</span>
                ) : (
                  <span onClick={() => reSort("sales")}>Best Selling</span>
                )}
                <span onClick={() => reSort("sales")}>Popular</span>
              </div>
            )}
          </div>
        </div>
        <div className="cards">
          <ChakraProvider>
            {gigs.map((gig) => (
              <GigCard key={gig.id} item={gig} />
            ))}
          </ChakraProvider>
        </div>
      </div>
    </div>
  );
}

export default Gigs;
