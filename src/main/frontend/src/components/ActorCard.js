import React from "react";

const actors = [
    {name: "Ben Whishaw", role: "Paddington (Voice)", img: "URL"},
    {name: "Hugh Bonneville", role: "Henry Brown", img: "URL"},
    {name: "Sally Hawkins", role: "Mary Brown", img: "URL"},
];

export default function ActorCard() {
    return (
        <div className="overflow-x-auto whitespace-nowrap p-4">
            <div className="flex space-x-4">
                {actors.map((actor, index) => (
                    <div key={index} className="bg-white p-4 rounded-2xl shadow-md text-center min-w-[200px]">
                        <img src={actor.img} alt={actor.name} className="w-full h-40 object-cover rounded-lg"/>
                        <h2 className="text-lg font-bold mt-2">{actor.name}</h2>
                        <p className="text-sm text-gray-500">{actor.role}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}