import React, { useEffect, useState } from "react";
import "./GigCard.scss";
import { Link } from "react-router-dom";
import { hosts } from "../../const.js";
import {
  Box,
  Center,
  Heading,
  Text,
  Stack,
  Avatar,
  useColorModeValue,
  Image,
} from "@chakra-ui/react";

export default function GigCard({ item }) {
  const linkTo = "/gig/" + item.id;
  const date = new Date(item.date);
  const dateWithoutTime = new Date(
    date.getFullYear(),
    date.getMonth(),
    date.getDate()
  ).toLocaleDateString("fr-FR");
  return (
    <Link to={linkTo} className="link">
      <Center py={6}>
        <Box
          // maxW={"445px"}
          width={"400px"}
          height={"450px"}
          // w={"full"}
          bg={useColorModeValue("white", "gray.900")}
          boxShadow={"2xl"}
          rounded={"md"}
          p={6}
          overflow={"hidden"}
        >
          <Box
            h={"210px"}
            bg={"gray.100"}
            mt={-6}
            mx={-6}
            mb={6}
            pos={"relative"}
          >
            <Image
              src={item.image}
              objectFit={"cover"}
              alt="Offer image"
              width={"100%"}
              height={"100%"}
            />
          </Box>
          <Stack>
            <Text
              color={"green.500"}
              textTransform={"uppercase"}
              fontWeight={800}
              fontSize={"sm"}
              letterSpacing={1.1}
            >
              {item.price + "€"}
            </Text>
            <Heading
              color={useColorModeValue("gray.700", "white")}
              fontSize={"2xl"}
              fontFamily={"body"}
            >
              {item.title}
            </Heading>
            <Text color={"gray.500"}>{item.description.substring(0, 70)}</Text>
          </Stack>
          <Stack mt={6} direction={"row"} spacing={4} align={"center"}>
            <Avatar
              src={"https://avatars0.githubusercontent.com/u/1164541?v=4"}
            />
            <Stack direction={"column"} spacing={0} fontSize={"sm"}>
              <Text fontWeight={600}>{item?.traveler?.user?.username}</Text>
              <Text color={"gray.500"}>{dateWithoutTime}</Text>
            </Stack>
          </Stack>
        </Box>
      </Center>
    </Link>
  );
}
