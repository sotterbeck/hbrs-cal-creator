'use client'
import React from 'react';
import TeachingEventList from "@/components/teachingEvents/select/mobile-list/teaching-event-list";
import {DataTable} from "@/components/teachingEvents/select/table/data-table";
import {columns} from "@/components/teachingEvents/select/table/table-columns";
import {SelectedEventsState} from "@/components/teachingEvents/teaching-events";
import TeachingEventFilter from "@/components/teachingEvents/select/filter/teaching-event-filter";

interface TeachingEventsSelectProps {
    teachingEvents: EventModel[];
    selectedEvents: SelectedEventsState;
    setSelectedEvents: React.Dispatch<React.SetStateAction<SelectedEventsState>>;
    searchQuery: string;
    setSearchQuery: React.Dispatch<React.SetStateAction<string>>;
}

export default function TeachingEventsSelect(props: TeachingEventsSelectProps) {
    return (
        <>
            <TeachingEventFilter
                searchQuery={props.searchQuery}
                setSearchQuery={props.setSearchQuery}
            ></TeachingEventFilter>
            <TeachingEventList
                teachingEvents={props.teachingEvents}
                selectedEvents={props.selectedEvents}
                setSelectedEvents={props.setSelectedEvents}
                className="pb-20 lg:hidden"
            />
            <DataTable
                columns={columns}
                data={props.teachingEvents}
                rowSelection={props.selectedEvents}
                setRowSelection={props.setSelectedEvents}
                className={'hidden lg:block'}
            />
        </>
    );
}